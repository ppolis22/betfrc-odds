package com.buzz.bbevent.controller;

import com.buzz.bbevent.service.EventService;
import com.buzz.bbevent.entity.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/events")
public class EventController {

    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> allEvents = eventService.getAllEvents();
        return ResponseEntity.ok(allEvents);
    }

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
