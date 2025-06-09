package com.buzz.betfrcodds.dto;

import java.util.List;

public class PropDto {
    private String id;
    private String type;
    private List<PropValueDto> values;

    public PropDto() {}

    public PropDto(String id, String type, List<PropValueDto> values) {
        this.id = id;
        this.type = type;
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PropValueDto> getValues() {
        return values;
    }

    public void setValues(List<PropValueDto> values) {
        this.values = values;
    }
}
