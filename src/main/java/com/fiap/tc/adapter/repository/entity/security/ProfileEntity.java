package com.fiap.tc.adapter.repository.entity.security;

import com.fiap.tc.core.domain.enums.ProfileSystem;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static java.lang.String.format;

@Entity
@Data
@Table(name = "profile", schema = "security")
public class ProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Enumerated(EnumType.STRING)
    private ProfileSystem id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "roles_profile", schema = "security",
            joinColumns = {@JoinColumn(name = "id_profile")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    private Set<RoleEntity> roles;

    @Override
    public String toString() {
        return format("Profile{id:%s, nome:%s}", id, name);
    }

}
