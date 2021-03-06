package com.hubfintech.app.services;

import java.util.List;

import com.hubfintech.app.dtos.HistoricoDto;

public interface HistoricoService {

	public Long cadastrarOuAtualizar(HistoricoDto historicoDto);
	public HistoricoDto consultarPeloId(Long id);
	public List<HistoricoDto> consultarTodos();
	
}
