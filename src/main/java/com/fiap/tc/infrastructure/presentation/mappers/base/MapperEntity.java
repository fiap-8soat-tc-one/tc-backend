package com.fiap.tc.infrastructure.presentation.mappers.base;

import org.mapstruct.InheritInverseConfiguration;

public interface MapperEntity<E, D> {

    E toDomain(D dto);

    @InheritInverseConfiguration
    D fromDomain(E entity);
}
