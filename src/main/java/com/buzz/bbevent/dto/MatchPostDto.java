package com.buzz.bbevent.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MatchPostDto {
    private Integer matchNumber;
    private LocalDateTime matchTime;
    List<String> redTeams;
    List<String> blueTeams;

    public MatchPostDto() { }

    public MatchPostDto(Integer matchNumber, LocalDateTime matchTime, List<String> redTeams, List<String> blueTeams) {
        this.matchNumber = matchNumber;
        this.matchTime = matchTime;
        this.redTeams = redTeams;
        this.blueTeams = blueTeams;
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

    public List<String> getRedTeams() {
        return redTeams;
    }

    public void setRedTeams(List<String> redTeams) {
        this.redTeams = redTeams;
    }

    public List<String> getBlueTeams() {
        return blueTeams;
    }

    public void setBlueTeams(List<String> blueTeams) {
        this.blueTeams = blueTeams;
    }
}
