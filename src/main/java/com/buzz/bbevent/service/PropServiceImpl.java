package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropPostDto;
import com.buzz.bbevent.dto.PropValueDto;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.entity.PropType;
import com.buzz.bbevent.entity.PropValue;
import com.buzz.bbevent.entity.PropValueId;
import com.buzz.bbevent.exception.InvalidRequestException;
import com.buzz.bbevent.exception.MissingResourceException;
import com.buzz.bbevent.repo.PropRepository;
import com.buzz.bbevent.repo.PropTypeRepository;
import com.buzz.bbevent.repo.PropValueRepository;
import org.springframework.stereotype.Service;

@Service
public class PropServiceImpl implements PropService {

    private final PropRepository propRepository;
    private final PropValueRepository propValueRepository;
    private final PropTypeRepository propTypeRepository;

    public PropServiceImpl(PropRepository propRepository, PropTypeRepository propTypeRepository,
                           PropValueRepository propValueRepository) {
        this.propRepository = propRepository;
        this.propTypeRepository = propTypeRepository;
        this.propValueRepository = propValueRepository;
    }

    @Override
    public Prop getProp(String id) {
        return propRepository.findById(id).orElse(null);
    }

    @Override
    public Prop createProp(PropPostDto prop) {
        // TODO check if prop already exists with same parent and propType
        // TODO check if propValues contains duplicates
        if (prop.getPropTypeId() == null || prop.getPropValues().size() == 0) {
            return null;
        }
        PropType propType = propTypeRepository.findById(prop.getPropTypeId()).orElse(null);
        Prop newProp = new Prop(null,
                propType,
                prop.getParentId());
        Prop createdProp = propRepository.save(newProp);

        for (PropValueDto propValue : prop.getPropValues()) {
            PropValueId newPropValueId = new PropValueId(propValue.getValue(), createdProp.getId());
            PropValue newPropValue = new PropValue(
                    newPropValueId,
                    createdProp,
                    propValue.getOdds());
            propValueRepository.save(newPropValue);
        }

        return getProp(createdProp.getId());    // TODO find out why this doesn't work
    }

    @Override
    public void addPropValue(String propId, PropValueDto propValue)
            throws MissingResourceException, InvalidRequestException {
        Prop prop = propRepository.findById(propId).orElseThrow(MissingResourceException::new);
        for (PropValue existingValue : prop.getValues()) {
            if (existingValue.getId().getPropValue().equals(propValue.getValue())) {
                throw new InvalidRequestException();
            }
        }
        PropValueId propValueId = new PropValueId(propValue.getValue(), propId);
        PropValue newPropValue = new PropValue(propValueId, prop, propValue.getOdds());
        propValueRepository.save(newPropValue);
    }

    @Override
    public void removePropValue(String propId, PropValueDto propValue) {
        propValueRepository.deleteById(new PropValueId(propValue.getValue(), propId));
    }

    @Override
    public void updatePropValueOdds(String propId, String propValue, int odds)
            throws MissingResourceException {
        PropValue toUpdate = propValueRepository
                .findById(new PropValueId(propValue, propId))
                .orElseThrow(MissingResourceException::new);
        toUpdate.setOdds(odds);
        propValueRepository.save(toUpdate);

        // TODO send out WebSockets update
    }
}
