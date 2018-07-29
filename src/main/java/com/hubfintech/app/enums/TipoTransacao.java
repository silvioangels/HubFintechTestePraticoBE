package com.hubfintech.app.enums;

public enum TipoTransacao {

	CADASTRAR, ALTERAR, EXCLUIR, CARGA, TRANFERENCIA;

	public static TipoTransacao recuperarEnum(String codigo) {

		for (TipoTransacao registro : values()) {

			if (registro.name().equals(codigo.trim())) {
				return registro;
			}

		}

		throw new IllegalArgumentException("Valor do codigo não valido");
	}

}
