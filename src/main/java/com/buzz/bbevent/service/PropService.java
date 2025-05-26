package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropPostDto;
import com.buzz.bbevent.dto.PropValueDto;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.entity.PropValue;

import java.util.List;

public interface PropService {
    Prop getProp(String id);
    Prop createProp(PropPostDto prop);
    void setPropValues(String propId, List<PropValueDto> propValues);
    void updatePropValueOdds(String propId, PropValueDto propValue);
}
