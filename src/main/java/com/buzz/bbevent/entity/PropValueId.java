package com.buzz.bbevent.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PropValueId implements Serializable {
    private String propValue;
    private String propId;

    public PropValueId() {}

    public PropValueId(String propValue, String propId) {
        this.propValue = propValue;
        this.propId = propId;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String value) {
        this.propValue = value;
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropValueId that = (PropValueId) o;
        return propValue.equals(that.propValue) && propId.equals(that.propId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propValue, propId);
    }
}
