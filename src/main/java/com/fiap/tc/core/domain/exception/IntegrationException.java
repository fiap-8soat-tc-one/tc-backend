package com.fiap.tc.core.domain.exception;

public class IntegrationException extends BadRequestException {

	public IntegrationException() {
		super();
	}

	public IntegrationException(String message) {
		super(message);
	}
}
