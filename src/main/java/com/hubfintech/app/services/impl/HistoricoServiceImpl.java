package com.hubfintech.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubfintech.app.dtos.HistoricoDto;
import com.hubfintech.app.entities.Historico;
import com.hubfintech.app.enums.TipoTransacao;
import com.hubfintech.app.repositories.HistoricoRepository;
import com.hubfintech.app.services.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService{

	@Autowired
	private HistoricoRepository repository;

	@Override
	public Long cadastrarOuAtualizar(HistoricoDto historicoDto) {
		
		Historico entity = new Historico();
		
		entity.setValor(historicoDto.getValor());
		entity.setTipoTransferencia(TipoTransacao.recuperarEnum(historicoDto.getTipoTransferencia()));
		
		repository.save(entity);
		
		entity.setContaOrigem(historicoDto.getContaOrigem());
		entity.setContaDestino(historicoDto.getContaDestino());
		
		repository.save(entity);
		
		return entity.getId();
		
	}

	@Override
	public List<HistoricoDto> consultarTodos() {
		
		List<HistoricoDto> listaHistorico = new ArrayList<HistoricoDto>();
		
		for (Historico historico : repository.findAll()) {
			HistoricoDto historicoDto = new HistoricoDto(); 
			
			historicoDto.setId(historico.getId());
			historicoDto.setContaOrigem(historico.getContaOrigem());
			historicoDto.setContaDestino(historico.getContaDestino());
			historicoDto.setValor(historico.getValor());
			historicoDto.setTipoTransferencia(historico.getTipoTransferencia().name());
			historicoDto.setDataCriacao(historico.getDataCriacao());
			
			listaHistorico.add(historicoDto);
		}
		
		return listaHistorico;
		
	}
	
	@Override
	public HistoricoDto consultarPeloId(Long id) {
		
		HistoricoDto historicoDto = new HistoricoDto();
		
		Historico entity = repository.findById(id).get();
		
		historicoDto.setId(entity.getId());
		historicoDto.setContaOrigem(entity.getContaOrigem());
		historicoDto.setContaDestino(entity.getContaDestino());
		historicoDto.setValor(entity.getValor());
		historicoDto.setTipoTransferencia(entity.getTipoTransferencia().name());
		historicoDto.setDataCriacao(entity.getDataCriacao());
		
		return historicoDto;
	}
	

}