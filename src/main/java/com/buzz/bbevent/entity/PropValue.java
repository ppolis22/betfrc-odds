package com.buzz.bbevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class PropValue {
    @EmbeddedId
    private PropValueId id;

    @ManyToOne
    @JsonIgnore
    @MapsId("propId")
    private Prop prop;

    private Integer odds;
    private Boolean isAlive;

    public PropValue() {}

    public PropValue(PropValueId id, Prop prop, Integer odds, Boolean isAlive) {
        this.id = id;
        this.prop = prop;
        this.odds = odds;
        this.isAlive = isAlive;
    }

    public PropValueId getId() {
        return id;
    }

    public void setId(PropValueId id) {
        this.id = id;
    }

    public Prop getProp() {
        return prop;
    }

    public void setProp(Prop prop) {
        this.prop = prop;
    }

    public Integer getOdds() {
        return odds;
    }

    public void setOdds(Integer odds) {
        this.odds = odds;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(Boolean alive) {
        isAlive = alive;
    }
}
