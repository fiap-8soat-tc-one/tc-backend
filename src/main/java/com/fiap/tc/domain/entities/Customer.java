package com.fiap.tc.domain.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {
    private UUID id;
    private String document;
    private String name;
    private String email;
}
