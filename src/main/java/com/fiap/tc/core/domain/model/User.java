package com.fiap.tc.core.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID uuid;
    private String name;
    private String email;
    private String login;
}
