package com.fiap.tc.adapter.repository.entity;

import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    private String name;

    @Column(name = "document", unique = true, length = 20)
    private String document;

    private String email;

    @Embedded
    private Audit audit;
}
