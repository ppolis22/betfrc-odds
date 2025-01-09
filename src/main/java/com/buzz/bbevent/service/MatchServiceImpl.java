package com.buzz.bbevent.service;

import com.buzz.bbevent.dto.MatchPostDto;
import com.buzz.bbevent.entity.Event;
import com.buzz.bbevent.entity.Match;
import com.buzz.bbevent.entity.Team;
import com.buzz.bbevent.entity.TeamAppearance;
import com.buzz.bbevent.repo.EventRepository;
import com.buzz.bbevent.repo.MatchRepository;
import com.buzz.bbevent.repo.TeamAppearanceRepository;
import com.buzz.bbevent.repo.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    MatchRepository matchRepository;
    EventRepository eventRepository;
    TeamRepository teamRepository;
    TeamAppearanceRepository teamAppearanceRepository;

    public MatchServiceImpl(MatchRepository matchRepository,
                            EventRepository eventRepository,
                            TeamRepository teamRepository,
                            TeamAppearanceRepository teamAppearanceRepository) {
        this.matchRepository = matchRepository;
        this.eventRepository = eventRepository;
        this.teamRepository = teamRepository;
        this.teamAppearanceRepository = teamAppearanceRepository;
    }

    @Override
    public Match getMatch(String id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Match> getMatchesForEvent(String id) {
        return matchRepository.findByEventId(id);
    }

    @Override
    public void createMatch(String eventId, MatchPostDto matchData) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        List<Team> redTeams = getTeams(matchData.getRedTeams());
        List<Team> blueTeams = getTeams(matchData.getBlueTeams());

        Match newMatch = new Match(null, event, null, matchData.getMatchNumber(), matchData.getMatchTime());
        matchRepository.save(newMatch);

        redTeams.forEach((team) -> {
            teamAppearanceRepository.save(new TeamAppearance(null, newMatch, team, "RED"));
        });

        blueTeams.forEach((team) -> {
            teamAppearanceRepository.save(new TeamAppearance(null, newMatch, team, "BLUE"));
        });
    }

    private List<Team> getTeams(List<String> ids) {
        return ids.stream()
                .map((id) -> teamRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + id)))
                .collect(Collectors.toList());
    }
}
