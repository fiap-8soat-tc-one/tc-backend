package com.fiap.tc.core.domain.model;

import java.security.Principal;
import java.util.UUID;

import lombok.Data;

@Data
public class Autenticacao implements Principal {
	
	private UUID uuid;
	
	@Override
	public String getName() {
		return uuid.toString();
	}
	
	

}
