package com.buzz.betfrcodds.controller;


import com.buzz.betfrcodds.dto.MatchPostDto;
import com.buzz.betfrcodds.entity.Match;
import com.buzz.betfrcodds.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class MatchController {

    MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable String id) {
        Match match = matchService.getMatch(id);
        if (match != null) {
            return ResponseEntity.ok(match);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/events/{id}/matches")
    public ResponseEntity<List<Match>> getMatchesForEvent(@PathVariable String id) {
        List<Match> matches = matchService.getMatchesForEvent(id);
        return ResponseEntity.ok(matches);
    }

    @PostMapping("/events/{id}/matches")
    public ResponseEntity<String> createMatch(@PathVariable String id,
                                            @RequestBody MatchPostDto matchData) {
        try {
            matchService.createMatch(id, matchData);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully created match.", HttpStatus.CREATED);
    }
}
