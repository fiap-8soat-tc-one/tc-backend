package com.fiap.tc.adapters.repository.entity.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "security")
@Data
public class RoleEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "id_feature")
    private FeatureEntity feature;

}
