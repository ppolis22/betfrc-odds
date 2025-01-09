package com.buzz.bbevent.service;

import com.buzz.bbevent.entity.Event;
import com.buzz.bbevent.repo.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }
}
