package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.dto.PropDto;
import com.buzz.betfrcodds.dto.PropPostDto;
import com.buzz.betfrcodds.dto.PropQueryResponseDto;
import com.buzz.betfrcodds.dto.PropValueDto;
import com.buzz.betfrcodds.entity.*;
import com.buzz.betfrcodds.exception.InvalidRequestException;
import com.buzz.betfrcodds.exception.MissingResourceException;
import com.buzz.betfrcodds.repo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PropServiceImpl implements PropService {

    private final PropRepository propRepository;
    private final PropValueRepository propValueRepository;
    private final PropTypeRepository propTypeRepository;
    private final MatchRepository matchRepository;

    public PropServiceImpl(PropRepository propRepository, PropTypeRepository propTypeRepository,
                           PropValueRepository propValueRepository, MatchRepository matchRepository) {
        this.propRepository = propRepository;
        this.propTypeRepository = propTypeRepository;
        this.propValueRepository = propValueRepository;
        this.matchRepository = matchRepository;
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
        PropValue newPropValue = new PropValue(propValueId, prop, propValue.getOdds(), false);
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

    @Override
    public PropQueryResponseDto getMatchProps(String eventId, Integer matchNum) throws InvalidRequestException {
        List<PropDto> props = new ArrayList<>();
        List<Match> eventMatches = matchRepository.findByEventIdAndMatchNumber(eventId, matchNum);
        if (eventMatches.size() != 1) throw new InvalidRequestException();

        List<Prop> matchProps = propRepository.findByParentId(eventMatches.get(0).getId());
        for (Prop prop : matchProps) {
            List<PropValueDto> propValues = prop.getValues().stream()
                    .map(v -> new PropValueDto(v.getId().getPropValue(), v.getOdds(), v.isActive()))
                    .collect(Collectors.toList());
            props.add(new PropDto(prop.getId(), prop.getType().getLabel(), propValues));
        }

        return new PropQueryResponseDto(props);
    }
}
