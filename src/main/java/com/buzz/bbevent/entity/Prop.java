package com.buzz.bbevent.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Prop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private PropType type;

    @OneToMany(mappedBy = "prop")
    private List<PropValue> values;

    private String parentId;

    public Prop() { }

    public Prop(String id, PropType type, String parentId) {
        this.id = id;
        this.type = type;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<PropValue> getValues() {
        return values;
    }

    public void setValues(List<PropValue> values) {
        this.values = values;
    }
}
