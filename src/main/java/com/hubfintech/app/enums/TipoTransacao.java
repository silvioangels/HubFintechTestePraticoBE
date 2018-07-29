package com.hubfintech.app.enums;

public enum TipoTransacao {

	CADASTRAR ("C","CADASTRAR"), 
	ALTERAR ("A","ALTERAR"), 
	EXCLUIR ("E","EXCLUIR"), 
	CARGA ("CG","CARGA"), 
	TRANFERENCIA ("TR","TRANSFERENCIA");

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

	private TipoTransacao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static TipoTransacao recuperarEnum(String codigo) {

		for (TipoTransacao registro : values()) {

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
