package com.buzz.bbevent.dto;

import com.buzz.bbevent.entity.PropType;

import java.util.List;

public class PropPostDto {
    private String propTypeId;
    private String parentId;
    private List<PropValueDto> propValues;

    public PropPostDto() {}

    public PropPostDto(String propTypeId, String parentId, List<PropValueDto> propValues) {
        this.propTypeId = propTypeId;
        this.parentId = parentId;
        this.propValues = propValues;
    }

    public String getPropTypeId() {
        return propTypeId;
    }

    public void setPropTypeId(String propTypeId) {
        this.propTypeId = propTypeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<PropValueDto> getPropValues() {
        return propValues;
    }

    public void setPropValues(List<PropValueDto> propValues) {
        this.propValues = propValues;
    }
}
