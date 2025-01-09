package com.buzz.bbevent.controller;

import com.buzz.bbevent.entity.Team;
import com.buzz.bbevent.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/teams")
public class TeamController {

    TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> allTeams = teamService.getAllTeams();
        return ResponseEntity.ok(allTeams);
    }

    @PostMapping
    public ResponseEntity<Team> createEvent(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

}
