package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.dto.PropTypePostDto;
import com.buzz.betfrcodds.entity.PropType;

public interface PropTypeService {
    PropType createPropType(PropTypePostDto propTypePostDto);
}
