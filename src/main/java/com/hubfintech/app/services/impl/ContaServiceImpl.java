package com.hubfintech.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubfintech.app.dtos.ContaDto;
import com.hubfintech.app.entities.Conta;
import com.hubfintech.app.enums.SituacaoConta;
import com.hubfintech.app.enums.TipoConta;
import com.hubfintech.app.repositories.ContaRepository;
import com.hubfintech.app.services.ContaService;

@Service
public class ContaServiceImpl implements ContaService{

	@Autowired
	private ContaRepository repository;
	
	@Override
	public ContaDto cadastrarOuAtualizar(ContaDto contaDto) {
		
		Conta entity = new Conta();
		
		entity.setId(contaDto.getId());
		entity.setNome(contaDto.getNome());
		entity.setIdPai(contaDto.getIdPai());
		entity.setSaldo(contaDto.getSaldo());
		entity.setSituacao(SituacaoConta.recuperarEnum(contaDto.getSituacao()));
		entity.setTipoConta(TipoConta.recuperarEnum(contaDto.getTipoConta()));
		
		repository.save(entity);
		
		return recuperarPeloId(entity.getId());
	}

	@Override
	public List<ContaDto> recuperarTodos() {
		List<ContaDto> listaConta = new ArrayList<ContaDto>();
		
		for (Conta conta : repository.findAll()) {
			ContaDto contaDto = new ContaDto(); 
			
			contaDto.setId(conta.getId());
			contaDto.setIdPai(conta.getIdPai());
			contaDto.setNome(conta.getNome());
			contaDto.setSaldo(conta.getSaldo());
			contaDto.setSituacao(conta.getSituacao().name());
			contaDto.setTipoConta(conta.getTipoConta().name());
			contaDto.setContas(conta.getContas());
			contaDto.setDataCriacao(conta.getDataCriacao());
			
			listaConta.add(contaDto);
		}
		
		return listaConta;
	}

	@Override
	public ContaDto recuperarPeloId(Long id) {
		
		ContaDto contaDto = new ContaDto();
		
		Conta conta = repository.findById(id).get();
		
		contaDto.setId(conta.getId());
		contaDto.setIdPai(conta.getIdPai());
		contaDto.setNome(conta.getNome());
		contaDto.setSaldo(conta.getSaldo());
		contaDto.setSituacao(conta.getSituacao().name());
		contaDto.setTipoConta(conta.getTipoConta().name());
		contaDto.setContas(conta.getContas());
		
		return contaDto;
	}

	@Override
	public void deletar(Long id) {
		
		repository.deleteById(id);
		
	}

}