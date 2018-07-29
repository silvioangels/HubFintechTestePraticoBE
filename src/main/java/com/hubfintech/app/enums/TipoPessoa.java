package com.hubfintech.app.enums;

public enum TipoPessoa {
	
	FISICA,
	JURIDICA;
	
	public static TipoPessoa recuperarEnum(String descricao) {
		for (TipoPessoa tipo : TipoPessoa.values()) {
			  if(tipo.name().equals(descricao)) {
				  return tipo;
			  }
			}
		return FISICA;
	}

}
