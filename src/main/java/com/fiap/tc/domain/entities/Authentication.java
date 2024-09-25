package com.fiap.tc.domain.entities;

import lombok.Data;

import java.security.Principal;
import java.util.UUID;

@Data
public class Authentication implements Principal {

    private UUID uuid;

    @Override
    public String getName() {
        return uuid.toString();
    }
}
