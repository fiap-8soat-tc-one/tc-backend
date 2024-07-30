package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sistema", schema = "seguranca")
@Data
public class System {

    @Id
    private String id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean enabled;


}
