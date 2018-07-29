package com.hubfintech.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hubfintech.app.enums.TipoPessoa;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String numeroCpfCnpj;
	private String nome;
	private String razaoSocial;
	private Date dataNascimento;
	private TipoPessoa tipoPessoa;
	private Conta conta;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nr_cpf_cnpj", nullable = true)
	public String getNumeroCpfCnpj() {
		return numeroCpfCnpj;
	}

	public void setNumeroCpfCnpj(String numeroCpfCnpj) {
		this.numeroCpfCnpj = numeroCpfCnpj;
	}

	@Column(name = "nome", nullable = true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "razao_social", nullable = true)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@Column(name = "data_nascimento", nullable = false)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Column(name = "tipo_pessoa", nullable = true)
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", numeroCpfCnpj=" + numeroCpfCnpj + ", nome=" + nome + ", razaoSocial="
				+ razaoSocial + ", dataNascimento=" + dataNascimento + ", tipoPessoa=" + tipoPessoa + ", conta=" + conta
				+ "]";
	}
	
}
