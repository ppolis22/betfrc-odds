package com.buzz.bbevent.service;

import com.buzz.bbevent.entity.Team;
import com.buzz.bbevent.repo.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }
}
