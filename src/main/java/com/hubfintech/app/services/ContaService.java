package com.hubfintech.app.services;

import java.util.List;

import com.hubfintech.app.dtos.ContaDto;
import com.hubfintech.app.dtos.HistoricoDto;
import com.hubfintech.app.exception.RegraNegocioException;

public interface ContaService {
	
	public ContaDto cadastrarOuAtualizar(ContaDto contaDto);
	public List<ContaDto> consultarTodos();
	public ContaDto consultarPeloId(Long id);
	public void deletar(Long id);
	public void realizarTransferencia(HistoricoDto historicoDto) throws RegraNegocioException;
	public void realizarAporte(HistoricoDto historicoDto) throws RegraNegocioException;
	public void realizarEstorno(HistoricoDto historicoDto) throws RegraNegocioException;

}
