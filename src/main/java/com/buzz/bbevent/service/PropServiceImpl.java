package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropPostDto;
import com.buzz.bbevent.dto.PropValueDto;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.entity.PropType;
import com.buzz.bbevent.entity.PropValue;
import com.buzz.bbevent.entity.PropValueId;
import com.buzz.bbevent.repo.PropRepository;
import com.buzz.bbevent.repo.PropTypeRepository;
import com.buzz.bbevent.repo.PropValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    propValue.getOdds(),
                    true);
            propValueRepository.save(newPropValue);
        }

        return getProp(createdProp.getId());    // to populate the propValue list
    }

    @Override
    public void setPropValues(String propId, List<PropValueDto> propValues) {
        Prop propToUpdate = propRepository.findById(propId).orElse(null);
        if (propToUpdate == null) {
            // TODO throw something
        }

        propValueRepository.deleteByPropId(propToUpdate.getId());

        for (PropValueDto propValue : propValues) {
            PropValueId id = new PropValueId(propValue.getValue(), propToUpdate.getId());
            PropValue newPropValue = new PropValue(id, propToUpdate, propValue.getOdds(), true);
            propValueRepository.save(newPropValue);
        }
    }

    @Override
    public void updatePropValueOdds(String propId, PropValueDto propValue) {
//        Prop propToUpdate = propRepository.findById(propId).orElse(null);
//        if (propToUpdate == null) {
//            // TODO throw something
//        }
//
//        propValueRepository.
    }
}
