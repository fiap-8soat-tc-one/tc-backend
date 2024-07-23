package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupo", schema = "seguranca")
@Data
public class Grupo {

    @Id
    private String id;

    private String nome;
    private String descricao;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    @OneToMany(mappedBy = "grupo")
    private List<Permissao> permissoes;

}
