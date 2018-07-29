package com.hubfintech.app.enums;

public enum SituacaoConta {
	
	ATIVA,
	BLOQUEADA,
	CANCELADA;
	
	public static SituacaoConta recuperarEnum(String descricao) {
		for (SituacaoConta tipo : SituacaoConta.values()) {
			  if(tipo.name().equals(descricao)) {
				  return tipo;
			  }
			}
		return ATIVA;
	}

}
