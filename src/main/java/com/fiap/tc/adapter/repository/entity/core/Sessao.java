package com.fiap.tc.adapter.repository.entity.core;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao", schema = "seguranca")
@Data
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @Column(name = "dh_inicio")
    private LocalDateTime inicio;

    @Column(name = "dh_termino")
    private LocalDateTime termino;

    @Column(name = "ip_usuario")
    private String ipUsuario;

    @Column(name = "nm_servidor")
    private String servidor;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "qtd_acessos")
    private Integer qtdAcessos;

    @Column(name = "dh_ultimo_acesso")
    private LocalDateTime ultimoAcesso;


}
