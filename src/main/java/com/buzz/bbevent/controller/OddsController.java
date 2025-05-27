package com.buzz.bbevent.controller;

import com.buzz.bbevent.dto.*;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.entity.PropType;
import com.buzz.bbevent.entity.PropValue;
import com.buzz.bbevent.exception.InvalidRequestException;
import com.buzz.bbevent.exception.MissingResourceException;
import com.buzz.bbevent.service.PropService;
import com.buzz.bbevent.service.PropTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/props")
public class OddsController {

    private final PropService propsService;
    private final PropTypeService propTypesService;

    public OddsController(PropService propsService, PropTypeService propTypesService) {
        this.propsService = propsService;
        this.propTypesService = propTypesService;
    }

    @PostMapping()
    public ResponseEntity<Prop> createProp(@RequestBody PropPostDto prop) {
        Prop createdProp = propsService.createProp(prop);
        return new ResponseEntity<>(createdProp, HttpStatus.OK);
    }

    @GetMapping("/{propId}")
    public ResponseEntity<Prop> getProp(@PathVariable String propId) {
        Prop prop = propsService.getProp(propId);
        return new ResponseEntity<>(prop, HttpStatus.OK);
    }

    @PostMapping("/{propId}/value")
    public ResponseEntity<String> addPropValue(@PathVariable String propId,
                                                @RequestBody PropValueDto propValue) {
        try {
            propsService.addPropValue(propId, propValue);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvalidRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/{propId}/value")
    public ResponseEntity<String> updatePropValueOdds(@PathVariable String propId,
                                                      @RequestBody PropValueDto propValue) {
        try {
            propsService.updatePropValueOdds(propId, propValue.getValue(), propValue.getOdds());
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/{propId}/value")
    public ResponseEntity<String> deletePropValue(@PathVariable String propId,
                                                  @RequestBody PropValueDto propValue) {
        propsService.removePropValue(propId, propValue);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/type")
    public ResponseEntity<PropType> createPropType(@RequestBody PropTypePostDto propType) {
        PropType createdPropType = propTypesService.createPropType(propType);
        return new ResponseEntity<>(createdPropType, HttpStatus.OK);
    }

    @PostMapping("/query")
    public ResponseEntity<Integer> getOdds(@RequestBody OddsRequestDto oddsRequest) {
        Prop prop = propsService.getProp(oddsRequest.getPropId());
        if (prop != null && oddsRequest.getValue() != null) {
            for (PropValue propValue : prop.getValues()) {
                if (oddsRequest.getValue().equals(propValue.getId().getPropValue())) {
                    return new ResponseEntity<>(propValue.getOdds(), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
