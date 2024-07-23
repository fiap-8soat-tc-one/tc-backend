package com.fiap.tc.adapter.repository.entity.core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fiap.tc.core.domain.model.enums.StatusUsuario;
import com.fiap.tc.core.domain.model.enums.TipoDocumento;
import lombok.Data;

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
	@Column(name="tipo_documento", nullable = false, length = 100)
	private TipoDocumento tipoDocumento;
	
	@Column(name="num_documento")
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
		joinColumns = { @JoinColumn(name = "id_usuario") },
		inverseJoinColumns = { @JoinColumn(name = "id_perfil") }
	)
	private Set<Perfil> perfis;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoricoUsuario> historicos;
	
	



}
