package com.hubfintech.app.enums;

public enum TipoPessoa {

	TIPO_PESSOA_INVALIDO, FISICA, JURIDICA;

	public static TipoPessoa recuperarEnum(String codigo) {

		for (TipoPessoa registro : values()) {

			if (registro.name().equals(codigo.trim())) {
				return registro;
			}

		}

		return TIPO_PESSOA_INVALIDO;
	}

}
