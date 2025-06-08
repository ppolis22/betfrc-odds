package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.dto.PropTypePostDto;
import com.buzz.betfrcodds.entity.PropType;
import com.buzz.betfrcodds.repo.PropTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PropTypeServiceImpl implements PropTypeService {

    private final PropTypeRepository propTypeRepository;

    public PropTypeServiceImpl(PropTypeRepository propTypeRepository) {
        this.propTypeRepository = propTypeRepository;
    }

    @Override
    public PropType createPropType(PropTypePostDto propTypePostDto) {
        PropType newPropType = new PropType(
                propTypePostDto.getLabel(),
                propTypePostDto.getParentType());
        return propTypeRepository.save(newPropType);
    }
}
