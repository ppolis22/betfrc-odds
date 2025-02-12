package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropPostDto;
import com.buzz.bbevent.entity.Prop;

public interface PropService {
    Prop getProp(String id);
    Prop createProp(PropPostDto prop);
}
