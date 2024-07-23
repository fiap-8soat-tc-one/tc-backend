package com.fiap.tc.adapter.repository.entity.core;

import com.fiap.tc.core.domain.enums.StatusUsuario;
import com.fiap.tc.core.domain.enums.TipoDocumento;
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
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;
    private String login;
    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 100)
    private TipoDocumento tipoDocumento;

    @Column(name = "num_documento")
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 100)
    private StatusUsuario status;

    @Column(name = "dh_ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @Column(name = "qtd_tentativas")
    private Integer qtdTentativasInvalidas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_perfil", schema = "seguranca",
            joinColumns = {@JoinColumn(name = "id_usuario")},
            inverseJoinColumns = {@JoinColumn(name = "id_perfil")}
    )
    private Set<Perfil> perfis;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoricoUsuario> historicos;


}
