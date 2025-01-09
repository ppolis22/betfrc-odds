package com.buzz.bbevent.service;

import com.buzz.bbevent.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event createEvent(Event event);
}
