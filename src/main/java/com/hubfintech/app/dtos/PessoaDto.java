package com.hubfintech.app.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.hubfintech.app.entities.Conta;

@SuppressWarnings("deprecation")
public class PessoaDto {
	
	private Long id;
	private String numeroCpfCnpj;
	private String nome;
	private String razaoSocial;
	private Date dataNascimento;
	private String tipoPessoa;
	private Conta conta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCpfCnpj() {
		return numeroCpfCnpj;
	}

	public void setNumeroCpfCnpj(String numeroCpfCnpj) {
		this.numeroCpfCnpj = numeroCpfCnpj;
	}
	
	@NotNull(message = "Nome não pode ser vazia.")
	@Length(min = 5, max = 200, message = "Nome deve conter entre 5 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "Razao não pode ser vazia.")
	@Length(min = 5, max = 200, message = "Razao deve conter entre 5 e 200 caracteres.")
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotEmpty(message = "Tipo Pessoa não pode ser vazia.")
	@NotNull(message = "Tipo Pessoa não pode ser nula.")
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}