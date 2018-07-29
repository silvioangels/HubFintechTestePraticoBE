package com.hubfintech.app.services;

import java.util.List;

import com.hubfintech.app.dtos.PessoaDto;

public interface PessoaService {

	public void cadastrarOuAtualizar(PessoaDto contaDto);
	public List<PessoaDto> recuperarTodos();
	public PessoaDto recuperarPeloId(Long id);
	public void deletar(Long id);
	
}
