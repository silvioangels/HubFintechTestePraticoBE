package com.hubfintech.app.services;

import java.util.List;
import com.hubfintech.app.dtos.ContaDto;

public interface ContaService {
	
	public ContaDto cadastrarOuAtualizar(ContaDto contaDto);
	public List<ContaDto> recuperarTodos();
	public ContaDto recuperarPeloId(Long id);
	public void deletar(Long id);

}
