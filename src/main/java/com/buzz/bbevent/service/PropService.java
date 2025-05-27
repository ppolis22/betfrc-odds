package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.PropPostDto;
import com.buzz.bbevent.dto.PropValueDto;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.exception.InvalidRequestException;
import com.buzz.bbevent.exception.MissingResourceException;

import java.util.List;

public interface PropService {
    Prop getProp(String id);
    Prop createProp(PropPostDto prop);
    void addPropValue(String propId, PropValueDto propValue) throws MissingResourceException, InvalidRequestException;
    void removePropValue(String propId, PropValueDto propValue);
    void updatePropValueOdds(String propId, String propValue, int odds) throws MissingResourceException;
}
