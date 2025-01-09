package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.MatchPostDto;
import com.buzz.bbevent.entity.Match;

import java.util.List;

public interface MatchService {
    Match getMatch(String id);
    List<Match> getMatchesForEvent(String id);
    void createMatch(String eventId, MatchPostDto matchData);
}
