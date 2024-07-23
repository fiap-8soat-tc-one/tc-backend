package com.fiap.tc.core.domain.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Usuario {
	private UUID uuid;
	private String nome;
	private String email;
	private String login;
}
