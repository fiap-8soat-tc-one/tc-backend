package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permissao", schema = "seguranca")
@Data
public class Permissao {

    @Id
    private String id;

    private String nome;
    private String descricao;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Group grupo;

}
