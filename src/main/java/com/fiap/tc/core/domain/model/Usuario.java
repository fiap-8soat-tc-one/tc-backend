package com.fiap.tc.core.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Usuario {
    private UUID uuid;
    private String nome;
    private String email;
    private String login;
}
