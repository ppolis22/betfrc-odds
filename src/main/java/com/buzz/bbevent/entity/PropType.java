package com.buzz.bbevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PropType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<Prop> props;

    private String label;
    private String parentType;  // TODO convert to enum. Also might not even need this?

    public PropType() {}

    public PropType(String label, String parentType) {
        this(null, null, label, parentType);
    }

    public PropType(String id, List<Prop> props, String label, String parentType) {
        this.id = id;
        this.props = props;
        this.label = label;
        this.parentType = parentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Prop> getProps() {
        return props;
    }

    public void setProps(List<Prop> props) {
        this.props = props;
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
