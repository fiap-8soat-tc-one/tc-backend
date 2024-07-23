package com.fiap.tc.adapter.repository.mapper.base;

import org.mapstruct.InheritInverseConfiguration;

public interface MapperEntity<E, D> {

	E toEntity(D dto);

	@InheritInverseConfiguration
	D fromEntity(E entity);
}
