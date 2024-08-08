package com.fiap.tc.adapters.driven.infrastructure.persistence.entities.security;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feature", schema = "security")
@Data
public class FeatureEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "id_system_module")
    private ModuleEntity module;

    @OneToMany(mappedBy = "feature")
    private List<RoleEntity> roles;

}
