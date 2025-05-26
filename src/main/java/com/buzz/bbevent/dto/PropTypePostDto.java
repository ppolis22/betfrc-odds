package com.buzz.bbevent.dto;

public class PropTypePostDto {
    private String label;
    private String parentType;

    public PropTypePostDto() {}

    public PropTypePostDto(String label, String parentType) {
        this.label = label;
        this.parentType = parentType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}
