package com.fiap.tc.adapter.repository.entity.core;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

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