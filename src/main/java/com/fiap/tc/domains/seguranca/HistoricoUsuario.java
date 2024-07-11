package com.fiap.tc.domains.seguranca;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fiap.tc.domains.enums.OperacaoUsuario;
import lombok.Data;

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
	private Usuario usuario;
	
	@Column(name = "tipo_operacao", nullable = false)
	private OperacaoUsuario tipoOperacao;
	
	@Column(name = "dh_registro", nullable = false)
	private LocalDateTime dataHoraRegistro;


}
