package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event createEvent(Event event);
}
