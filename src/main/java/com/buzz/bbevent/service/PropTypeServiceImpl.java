package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropTypePostDto;
import com.buzz.bbevent.entity.PropType;
import com.buzz.bbevent.repo.PropTypeRepository;
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
