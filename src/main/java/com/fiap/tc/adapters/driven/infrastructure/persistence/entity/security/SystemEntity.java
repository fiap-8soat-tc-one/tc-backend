package com.fiap.tc.adapters.driven.infrastructure.persistence.entity.security;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system", schema = "security")
@Data
public class SystemEntity {

    @Id
    private String id;

    private String name;

    private String description;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;


}
