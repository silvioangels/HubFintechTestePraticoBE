package com.hubfintech.app.enums;

public enum TipoTransacao {

	TRANSACAO_INVALIDA, APORTE, TRANFERENCIA;

	public static TipoTransacao recuperarEnum(String codigo) {

		for (TipoTransacao registro : values()) {

			if (registro.name().equals(codigo.trim())) {
				return registro;
			}

		}

		return TRANSACAO_INVALIDA;
	}

}
