package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.dto.MatchPostDto;
import com.buzz.betfrcodds.entity.Match;

import java.util.List;

public interface MatchService {
    Match getMatch(String id);
    List<Match> getMatchesForEvent(String id);
    void createMatch(String eventId, MatchPostDto matchData);
}
