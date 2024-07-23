package com.fiap.tc.adapter.repository.entity.core;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "grupo", schema = "seguranca")
@Data
public class Grupo {

	@Id
	private String id;
	
	private String nome;
	private String descricao;
	
	@Column(name = "fl_ativo", columnDefinition = "boolean default true")
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name="id_modulo")
	private Modulo modulo;

	@OneToMany(mappedBy = "grupo")
	private List<Permissao> permissoes;
	
}
