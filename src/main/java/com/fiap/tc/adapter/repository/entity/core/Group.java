package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupo", schema = "seguranca")
@Data
public class Group {

    @Id
    private String id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Module module;

    @OneToMany(mappedBy = "group")
    private List<Role> roles;

}
