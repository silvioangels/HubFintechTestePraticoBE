package com.hubfintech.app.services;

import java.util.List;
import com.hubfintech.app.dtos.HistoricoDto;

public interface HistoricoService {

	public void cadastrarOuAtualizar(HistoricoDto historicoDto);
	public List<HistoricoDto> recuperarTodos();
	
}
