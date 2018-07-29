package com.hubfintech.app.enums;

public enum TipoConta {

	MATRIZ ("M","MATRIZ"), 
	FILIAL ("F","FILIAL");

	private String codigo;
	private String descricao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	private TipoConta(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static TipoConta recuperarEnum(String codigo) {
		
		for (TipoConta registro : values()) {
			
			if (registro.getCodigo().equals(codigo.trim())) {
				return registro;
			}
			
			if (registro.getDescricao().equals(codigo.trim())) {
				return registro;
			}
			
		}
		
		throw new IllegalArgumentException("Valor do codigo não valido");
	}

}
