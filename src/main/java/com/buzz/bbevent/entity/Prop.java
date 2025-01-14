package com.buzz.bbevent.entity;

import jakarta.persistence.*;

@Entity
public class Prop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private PropType type;

    private String parentId;
    private String propValue;
    private Integer odds;

    // TODO convert to enum
    private String parentType;

    public Prop() { }

    public Prop(String id, String parentType, PropType type, String parentId, String propValue, Integer odds) {
        this.id = id;
        this.parentType = parentType;
        this.type = type;
        this.parentId = parentId;
        this.propValue = propValue;
        this.odds = odds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public PropType getType() {
        return type;
    }

    public void setType(PropType type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public Integer getOdds() {
        return odds;
    }

    public void setOdds(Integer odds) {
        this.odds = odds;
    }
}
