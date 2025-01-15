package com.buzz.bbevent.controller;

import com.buzz.bbevent.dto.PropResponseDto;
import com.buzz.bbevent.dto.PropQueryDto;
import com.buzz.bbevent.entity.Prop;
import com.buzz.bbevent.service.PropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/odds")
public class OddsController {

    private final PropService propsService;

    public OddsController(PropService propsService) {
        this.propsService = propsService;
    }

    @PostMapping("/query")
    public ResponseEntity<PropResponseDto> getOdds(@RequestBody PropQueryDto query) {
        List<Prop> props = new ArrayList<>();
        List<String> missingIds = new ArrayList<>();

        for (String propId : query.getPropIds()) {
            Prop prop = propsService.getProp(propId);
            if (prop == null) {
                missingIds.add(propId);
            } else {
                props.add(prop);
            }
        }

        return new ResponseEntity<>(new PropResponseDto(props, missingIds), HttpStatus.OK);
    }
}
