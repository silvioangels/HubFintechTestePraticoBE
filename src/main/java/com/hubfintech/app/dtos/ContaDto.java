package com.hubfintech.app.dtos;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.hubfintech.app.entities.Conta;

@SuppressWarnings("deprecation")
public class ContaDto {
	
	private Long id;
	private String nome;
	private int idPai;
	private String tipoConta;
	private BigDecimal saldo;
	private String situacao;
	private List<Conta> contas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome não pode ser vazia.")
	@NotNull(message = "Nome não pode ser nula.")
	@Length(min = 5, max = 200, message = "Nome deve conter entre 5 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdPai() {
		return idPai;
	}

	public void setIdPai(int idPai) {
		this.idPai = idPai;
	}
	
	@NotEmpty(message = "Tipo Conta não pode ser vazia.")
	@NotNull(message = "Tipo Conta não pode ser nula.")
	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	@NotNull(message = "Limite de Credito deve ser preenchido")
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

}
