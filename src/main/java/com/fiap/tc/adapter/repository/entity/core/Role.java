package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permissao", schema = "seguranca")
@Data
public class Role {

    @Id
    private String id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Group group;

}
