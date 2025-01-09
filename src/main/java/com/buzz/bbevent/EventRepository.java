package com.buzz.bbevent;

import com.buzz.bbevent.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> { }
