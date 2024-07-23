package com.fiap.tc.adapter.repository.entity.core;

import com.fiap.tc.core.domain.enums.ProfileSystem;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "perfil", schema = "seguranca")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Enumerated(EnumType.STRING)
    private ProfileSystem id;

	@Column(name = "nome")
    private String name;

	@Column(name = "descricao")
    private String description;

    @Column(name = "fl_ativo", columnDefinition = "boolean default true")
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "perfil_permissao", schema = "seguranca",
            joinColumns = {@JoinColumn(name = "id_perfil")},
            inverseJoinColumns = {@JoinColumn(name = "id_permissao")}
    )
    private Set<Role> roles;

    @Override
    public String toString() {
        return String.format("PerfilAcesso{id:%s, nome:%s}", id, name);
    }

}
