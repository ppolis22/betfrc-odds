package com.buzz.betfrcodds.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Prop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private PropType type;

    @OneToMany(mappedBy = "prop", cascade = CascadeType.ALL)
    private List<PropValue> values;

    private String parentId;

    public Prop() { }

    public Prop(PropType type, String parentId) {
        this.type = type;
        this.parentId = parentId;
        this.values = new ArrayList<>();
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
