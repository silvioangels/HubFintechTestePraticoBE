package com.hubfintech.app.enums;

public enum TipoTransacao {
	
	CADASTRAR,
	ALTERAR,
	EXCLUIR,
	CARGA,
	TRANFERENCIA;
	
	public static TipoTransacao recuperarEnum(String descricao) {
		for (TipoTransacao tipo : TipoTransacao.values()) {
			  if(tipo.name().equals(descricao)) {
				  return tipo;
			  }
			}
		return CARGA;
	}

}
