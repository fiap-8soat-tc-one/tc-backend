package com.fiap.tc.core.domain.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class UserSystem {
    private UUID uuid;
    private String name;
    private String email;
    private String login;
}
