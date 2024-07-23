package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao", schema = "seguranca")
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity user;

    @Column(name = "dh_inicio")
    private LocalDateTime start;

    @Column(name = "dh_termino")
    private LocalDateTime end;

    @Column(name = "ip_usuario")
    private String userId;

    @Column(name = "nm_servidor")
    private String server;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "qtd_acessos")
    private Integer qtyAccesses;

    @Column(name = "dh_ultimo_acesso")
    private LocalDateTime lastAccess;


}
