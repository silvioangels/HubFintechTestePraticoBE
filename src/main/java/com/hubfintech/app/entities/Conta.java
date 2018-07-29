package com.hubfintech.app.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.hubfintech.app.enums.SituacaoConta;
import com.hubfintech.app.enums.TipoConta;

@Entity
@Table(name = "conta")
public class Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private int idPai;
	private TipoConta tipoConta;
	private BigDecimal saldo;
	private SituacaoConta situacao;
	private Date dataCriacao;
	private List<Conta> contas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "id_pai", nullable = true)
	public int getIdPai() {
		return idPai;
	}

	public void setIdPai(int idPai) {
		this.idPai = idPai;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta", nullable = true)
	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	@Column(name = "saldo", nullable = true)
	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", nullable = true)
	public SituacaoConta getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoConta situacao) {
		this.situacao = situacao;
	}
	
	@Column(name = "data_criacao", nullable = true)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@PrePersist
	public void prePersist() {
		dataCriacao = new Date();
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", nome=" + nome + ", idPai=" + idPai + ", tipoConta=" + tipoConta + ", saldo="
				+ saldo + ", situacao=" + situacao + ", dataCriacao=" + dataCriacao + ", contas=" + contas + "]";
	}

}
