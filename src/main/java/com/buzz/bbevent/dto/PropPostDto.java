package com.buzz.bbevent.dto;

import com.buzz.bbevent.entity.PropType;

public class PropPostDto {
    private String typeId;
    private String parentId;
    private String propValue;
    private Integer odds;
    private String parentType;

    public PropPostDto() {}

    public PropPostDto(String typeId, String parentId, String propValue, Integer odds, String parentType) {
        this.typeId = typeId;
        this.parentId = parentId;
        this.propValue = propValue;
        this.odds = odds;
        this.parentType = parentType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}
