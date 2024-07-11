package com.fiap.tc.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UsuarioDto {
	private UUID uuid;
	private String nome;
	private String email;
	private String login;
}
