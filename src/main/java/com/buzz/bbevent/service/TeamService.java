package com.buzz.bbevent.service;

import com.buzz.bbevent.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    void createTeam(Team team);
}
