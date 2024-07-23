package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "modulo", schema = "seguranca")
@Data
public class Modulo {

    @Id
    private String id;

    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_sistema")
    private Sistema sistema;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean ativo;


}
