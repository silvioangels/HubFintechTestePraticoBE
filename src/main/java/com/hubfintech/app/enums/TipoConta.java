package com.hubfintech.app.enums;

public enum TipoConta {
	
	MATRIZ,
	FILIAL;
	
	public static TipoConta recuperarEnum(String descricao) {
		for (TipoConta tipo : TipoConta.values()) {
			  if(tipo.name().equals(descricao)) {
				  return tipo;
			  }
			}
		return MATRIZ;
	}

}
