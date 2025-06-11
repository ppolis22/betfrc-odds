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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Transactional(rollbackFor = Exception.class)
    public Prop createProp(PropPostDto propDto) throws InvalidRequestException, MissingResourceException {
        String validationError = validatePropPostDto(propDto);
        if (validationError != null) throw new InvalidRequestException(validationError);

        PropType propType = propTypeRepository.findById(propDto.getPropTypeId())
                .orElseThrow(MissingResourceException::new);
        Prop newProp = new Prop(propType, propDto.getParentId());
        newProp = propRepository.save(newProp);

        for (PropValueDto propValue : propDto.getPropValues()) {
            PropValueId newPropValueId = new PropValueId(propValue.getValue(), newProp.getId());
            PropValue newPropValue = new PropValue(newPropValueId, newProp, propValue.getOdds(), true);
            newProp.getValues().add(newPropValue);
        }
        return propRepository.save(newProp);
    }

    @Override
    public void addPropValue(String propId, PropValueDto propValue)
            throws MissingResourceException, InvalidRequestException {
        Prop prop = propRepository.findById(propId).orElseThrow(MissingResourceException::new);
        for (PropValue existingValue : prop.getValues()) {
            if (existingValue.getId().getPropValue().equals(propValue.getValue())) {
                throw new InvalidRequestException("Prop Value already exists for Prop");
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

    private String validatePropPostDto(PropPostDto propDto) {
        if (propDto.getPropTypeId() == null) return "Prop must contain Prop Type";
        if (propDto.getPropValues().size() == 0) return "Prop must contain Prop Values";
        if (containsDuplicates(propDto.getPropValues())) return "Prop values must be unique";
        if (propRepository.existsByParentIdAndType_Id(propDto.getParentId(), propDto.getPropTypeId()))
            return "Prop of this type already exists for this parent";
        return null;
    }

    private boolean containsDuplicates(List<PropValueDto> propValues) {
        Set<String> values = new HashSet<>();
        for (PropValueDto propValue : propValues) {
            if (values.contains(propValue.getValue())) return true;
            values.add(propValue.getValue());
        }
        return false;
    }
}
