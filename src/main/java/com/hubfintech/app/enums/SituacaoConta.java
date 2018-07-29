package com.hubfintech.app.enums;

public enum SituacaoConta {

	ATIVA, 
	BLOQUEADA, 
	CANCELADA;
	
	public static SituacaoConta recuperarEnum(String codigo) {
		
		for (SituacaoConta registro : values()) {
			
			if (registro.equals(codigo.trim())) {
				return registro;
			}
			
		}
		
		throw new IllegalArgumentException("Valor do codigo não valido");
	}

}
