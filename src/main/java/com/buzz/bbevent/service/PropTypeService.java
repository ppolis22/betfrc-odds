package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropTypePostDto;
import com.buzz.bbevent.entity.PropType;

public interface PropTypeService {
    PropType createPropType(PropTypePostDto propTypePostDto);
}
