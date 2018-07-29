package com.hubfintech.app.enums;

public enum TipoPessoa {

	FISICA, JURIDICA;

	public static TipoPessoa recuperarEnum(String codigo) {

		for (TipoPessoa registro : values()) {

			if (registro.name().equals(codigo.trim())) {
				return registro;
			}

		}

		throw new IllegalArgumentException("Valor do codigo não valido");
	}

}
