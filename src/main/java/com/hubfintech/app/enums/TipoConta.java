package com.hubfintech.app.enums;

public enum TipoConta {

	MATRIZ, FILIAL;

	public static TipoConta recuperarEnum(String codigo) {

		for (TipoConta registro : values()) {

			if (registro.name().equals(codigo.trim())) {
				return registro;
			}

		}

		throw new IllegalArgumentException("Valor do codigo não valido");
	}

}
