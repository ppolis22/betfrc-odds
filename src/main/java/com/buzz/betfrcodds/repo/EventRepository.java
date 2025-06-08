package com.buzz.betfrcodds.repo;

import com.buzz.betfrcodds.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> { }
