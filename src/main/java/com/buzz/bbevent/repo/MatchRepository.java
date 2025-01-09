package com.buzz.bbevent.repo;

import com.buzz.bbevent.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, String> {
    List<Match> findByEventId(String eventId);
}
