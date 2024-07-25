package com.fiap.tc.adapter.repository.entity.core;

import com.fiap.tc.core.domain.enums.DocumentType;
import com.fiap.tc.core.domain.enums.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuario", schema = "seguranca",
        indexes = {
                @Index(name = "usuario_index_status", columnList = "status"),
                @Index(name = "usuario_index_tipo_doc", columnList = "tipo_documento")
        }
)
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;
    private String login;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 100)
    private DocumentType documentType;

    @Column(name = "num_documento")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 100)
    private UserStatus status;

    @Column(name = "dh_ultimo_acesso")
    private LocalDateTime lastAccess;

    @Column(name = "qtd_tentativas")
    private Integer qtyInvalidAttempts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_perfil", schema = "seguranca",
            joinColumns = {@JoinColumn(name = "id_usuario")},
            inverseJoinColumns = {@JoinColumn(name = "id_perfil")}
    )
    private Set<Profile> Profiles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserHistory> userHistories;


}
