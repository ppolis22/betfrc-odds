package com.buzz.bbevent.entity;

import jakarta.persistence.*;

@Entity
public class Prop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private PropParentType parentType;

    @ManyToOne
    private PropType type;

    private String parentId;
    private String propValue;
    private Integer odds;
}
