package com.fiap.tc.adapter.repository.entity.core;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fiap.tc.core.domain.enums.PerfilSistema;
import lombok.Data;

@Entity
@Data
@Table(name = "perfil", schema = "seguranca")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Enumerated(EnumType.STRING)
	private PerfilSistema id;
	
	private String nome;
	private String descricao;
	
	@Column(name = "fl_ativo", columnDefinition = "boolean default true")
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "perfil_permissao", schema = "seguranca",
		joinColumns = { @JoinColumn(name = "id_perfil") },
		inverseJoinColumns = { @JoinColumn(name = "id_permissao") }
	)
	private Set<Permissao> permissoes;
	
	@Override
	public String toString() {
		return String.format("PerfilAcesso{id:%s, nome:%s}", id, nome);
	}
	
}
