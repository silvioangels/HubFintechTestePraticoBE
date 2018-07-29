package com.hubfintech.app.enums;

public enum SituacaoConta {

	ATIVA ("A","ATIVA"), 
	BLOQUEADA ("B","BLOQUEADA"), 
	CANCELADA ("C","CANCELADA");
	
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

	private SituacaoConta(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static SituacaoConta recuperarEnum(String codigo) {
		
		for (SituacaoConta registro : values()) {
			
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
