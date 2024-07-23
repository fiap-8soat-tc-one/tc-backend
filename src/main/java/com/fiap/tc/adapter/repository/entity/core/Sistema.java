package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sistema", schema = "seguranca")
@Data
public class Sistema {

    @Id
    private String id;

    private String nome;

    private String descricao;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean ativo;


}
