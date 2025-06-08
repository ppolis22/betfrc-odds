package com.buzz.betfrcodds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @ManyToOne
    private Event event;

    @OneToMany(mappedBy = "match")
    private Set<TeamAppearance> teams;

    private Integer matchNumber;
    private LocalDateTime matchTime;

    public Match() {}

    public Match(String id, Event event, Set<TeamAppearance> teams, Integer matchNumber, LocalDateTime matchTime) {
        this.id = id;
        this.event = event;
        this.teams = teams;
        this.matchNumber = matchNumber;
        this.matchTime = matchTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<TeamAppearance> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamAppearance> teams) {
        this.teams = teams;
    }

    public Integer getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(Integer matchNumber) {
        this.matchNumber = matchNumber;
    }

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalDateTime matchTime) {
        this.matchTime = matchTime;
    }
}
