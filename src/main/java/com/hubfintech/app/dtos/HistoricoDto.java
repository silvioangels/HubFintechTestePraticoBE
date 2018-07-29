package com.hubfintech.app.dtos;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hubfintech.app.entities.Conta;

@SuppressWarnings("deprecation")
public class HistoricoDto {

	private Conta contaOrigem;
	private Conta contaDestino;
	private BigDecimal valor;
	private String tipoTransferencia;
	private Date dataCriacao;

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	
	@NotNull(message = "Conta de Destino não pode ser vazia.")
	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
	
	@NotNull(message = "Valor deve ser preenchido")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@NotEmpty(message = "Tipo Transferencia não pode ser vazia.")
	@NotNull(message = "Tipo Transferencia não pode ser nula.")
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}

	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	

}
