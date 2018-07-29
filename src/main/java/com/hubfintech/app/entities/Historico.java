package com.hubfintech.app.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.hubfintech.app.enums.TipoTransacao;

@Entity
@Table(name = "historico")
public class Historico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Conta contaOrigem;
	private Conta contaDestino;
	private BigDecimal valor;
	private TipoTransacao tipoTransferencia;
	private Date dataCriacao;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	public Conta getContaOrigem() {
		return contaOrigem;
	}
	
	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	@Column(name = "valor", nullable = true)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Column(name = "tipo_transferencia", nullable = true)
	public TipoTransacao getTipoTransferencia() {
		return tipoTransferencia;
	}

	public void setTipoTransferencia(TipoTransacao tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}
	
	@Column(name = "data_criacao", nullable = true)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@PrePersist
	public void prePersist() {
		dataCriacao = new Date();
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", contaOrigem=" + contaOrigem + ", contaDestino=" + contaDestino + ", valor="
				+ valor + ", tipoTransferencia=" + tipoTransferencia + ", dataCriacao=" + dataCriacao + "]";
	}
	
	

}
