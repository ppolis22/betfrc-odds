package com.buzz.betfrcodds.repo;

import com.buzz.betfrcodds.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, String> {
    List<Match> findByEventId(String eventId);
    List<Match> findByEventIdAndMatchNumber(String eventId, Integer matchNumber);
}
