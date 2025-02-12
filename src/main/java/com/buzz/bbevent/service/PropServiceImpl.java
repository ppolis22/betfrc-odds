package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropPostDto;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.entity.PropType;
import com.buzz.bbevent.repo.PropRepository;
import com.buzz.bbevent.repo.PropTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PropServiceImpl implements PropService {

    private final PropRepository propRepository;
    private final PropTypeRepository propTypeRepository;

    public PropServiceImpl(PropRepository propRepository, PropTypeRepository propTypeRepository) {
        this.propRepository = propRepository;
        this.propTypeRepository = propTypeRepository;
    }

    @Override
    public Prop getProp(String id) {
        return propRepository.findById(id).orElse(null);
    }

    @Override
    public Prop createProp(PropPostDto prop) {
        if (prop.getTypeId() == null) {
            return null;
        }
        PropType propType = propTypeRepository.findById(prop.getTypeId()).orElse(null);
        Prop newProp = new Prop(null,
                prop.getParentType(),
                propType,
                prop.getParentId(),
                prop.getPropValue(),
                prop.getOdds());
        return propRepository.save(newProp);
    }
}
