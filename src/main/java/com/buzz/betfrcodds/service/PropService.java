package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.dto.PropPostDto;
import com.buzz.betfrcodds.dto.PropQueryResponseDto;
import com.buzz.betfrcodds.dto.PropValueDto;
import com.buzz.betfrcodds.entity.Prop;
import com.buzz.betfrcodds.entity.PropValue;
import com.buzz.betfrcodds.exception.InvalidRequestException;
import com.buzz.betfrcodds.exception.MissingResourceException;

import java.util.List;

public interface PropService {
    Prop getProp(String id);
    Prop createProp(PropPostDto prop) throws InvalidRequestException, MissingResourceException;
    void addPropValue(String propId, PropValueDto propValue) throws MissingResourceException, InvalidRequestException;
    void removePropValue(String propId, PropValueDto propValue);
    void updatePropValueOdds(String propId, String propValue, int odds) throws MissingResourceException;
    PropQueryResponseDto getMatchProps(String eventId, Integer matchNum) throws InvalidRequestException;
}
