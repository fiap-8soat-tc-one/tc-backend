package com.fiap.tc.domains.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "modulo", schema = "seguranca")
@Data
public class Modulo {

	@Id
	private String id;
	
	private String nome;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="id_sistema")
	private Sistema sistema;
	
	@Column(name = "fl_ativo", columnDefinition = "boolean default true")
	private boolean ativo;

	
}
