package com.buzz.betfrcodds.dto;

public class PropValueDto {
    private String value;
    private Integer odds;

    public PropValueDto() {}

    public PropValueDto(String value, Integer odds, Boolean isActive) {
        this.value = value;
        this.odds = odds;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getOdds() {
        return odds;
    }

    public void setOdds(Integer odds) {
        this.odds = odds;
    }
}
