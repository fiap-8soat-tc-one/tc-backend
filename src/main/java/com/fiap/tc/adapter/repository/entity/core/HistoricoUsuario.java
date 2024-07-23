package com.fiap.tc.adapter.repository.entity.core;

import com.fiap.tc.core.domain.enums.OperacaoUsuario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_usuario", schema = "seguranca",
        indexes = {
                @Index(name = "historico_usuario_index_tipo_operacao", columnList = "tipo_operacao")
        }
)
@Data
public class HistoricoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "tipo_operacao", nullable = false)
    private OperacaoUsuario tipoOperacao;

    @Column(name = "dh_registro", nullable = false)
    private LocalDateTime dataHoraRegistro;


}
