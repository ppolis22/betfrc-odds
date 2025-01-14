package com.buzz.bbevent.service;

import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.repo.PropRepository;
import org.springframework.stereotype.Service;

@Service
public class PropServiceImpl implements PropService {

    private PropRepository propRepository;

    public PropServiceImpl(PropRepository propRepository) {
        this.propRepository = propRepository;
    }

    @Override
    public Prop getProp(String id) {
        return propRepository.findById(id).orElse(null);
    }
}
