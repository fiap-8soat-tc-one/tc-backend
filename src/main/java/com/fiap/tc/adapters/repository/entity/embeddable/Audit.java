package com.fiap.tc.adapters.repository.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active = true;
}
