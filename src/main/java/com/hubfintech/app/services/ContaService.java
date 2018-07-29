package com.hubfintech.app.services;

import java.util.List;
import com.hubfintech.app.dtos.ContaDto;

public interface ContaService {
	
	public List<ContaDto> cadastrarOuAtualizar(ContaDto contaDto);
	public List<ContaDto> recuperarTodos();
	public List<ContaDto> recuperarPeloId(Long id);
	public void deletar(Long id);

}
