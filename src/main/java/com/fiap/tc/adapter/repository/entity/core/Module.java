package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "modulo", schema = "seguranca")
@Data
public class Module {

    @Id
    private String id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_sistema")
    private System system;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean enabled;
    
}
