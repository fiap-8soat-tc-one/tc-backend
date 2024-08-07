package com.fiap.tc.infrastructure.adapter.persistence.entity.security;

import com.fiap.tc.core.domain.enums.DocumentType;
import com.fiap.tc.core.domain.enums.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_system", schema = "security",
        indexes = {
                @Index(name = "user_index_status", columnList = "status"),
                @Index(name = "user_index_doc_type", columnList = "document_type")
        }
)
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private String login;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 100)
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 100)
    private UserStatus status;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Column(name = "qty_invalid_attempts")
    private Integer qtyInvalidAttempts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_profile", schema = "security",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_profile")}
    )
    private Set<ProfileEntity> Profiles;


}
