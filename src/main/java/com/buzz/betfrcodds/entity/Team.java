package com.buzz.betfrcodds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<TeamAppearance> matches;

    private Integer number;
    private String name;

    public Team() {}

    public Team(String id, Set<TeamAppearance> matches, Integer number, String name) {
        this.id = id;
        this.matches = matches;
        this.number = number;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<TeamAppearance> getMatches() {
        return matches;
    }

    public void setMatches(Set<TeamAppearance> matches) {
        this.matches = matches;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
