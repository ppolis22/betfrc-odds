package com.buzz.betfrcodds.service;

import com.buzz.betfrcodds.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    Team createTeam(Team team);
}
