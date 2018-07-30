package com.hubfintech.app.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubfintech.app.dtos.ContaDto;
import com.hubfintech.app.dtos.HistoricoDto;
import com.hubfintech.app.entities.Conta;
import com.hubfintech.app.enums.SituacaoConta;
import com.hubfintech.app.enums.TipoConta;
import com.hubfintech.app.enums.TipoTransacao;
import com.hubfintech.app.exception.RegraNegocioException;
import com.hubfintech.app.repositories.ContaRepository;
import com.hubfintech.app.services.ContaService;

@Service
public class ContaServiceImpl implements ContaService{

	@Autowired
	private ContaRepository repository;
	
	@Override
	public Long cadastrarOuAtualizar(ContaDto contaDto) {
		
		Conta entity = new Conta();
		
		entity.setId(contaDto.getId());
		entity.setNome(contaDto.getNome());
		entity.setIdPai(contaDto.getIdPai());
		entity.setSaldo(contaDto.getSaldo());
		entity.setSituacao(SituacaoConta.recuperarEnum(contaDto.getSituacao()));
		entity.setTipoConta(TipoConta.recuperarEnum(contaDto.getTipoConta()));
		
		repository.save(entity);
		
		return entity.getId();
	}

	@Override
	public List<ContaDto> consultarTodos() {
		List<ContaDto> listaConta = new ArrayList<ContaDto>();
		
		for (Conta conta : repository.findAll()) {
			ContaDto contaDto = new ContaDto(); 
			
			contaDto.setId(conta.getId());
			contaDto.setIdPai(conta.getIdPai());
			contaDto.setNome(conta.getNome());
			contaDto.setSaldo(conta.getSaldo());
			contaDto.setSituacao(conta.getSituacao().name());
			contaDto.setTipoConta(conta.getTipoConta().name());
			contaDto.setDataCriacao(conta.getDataCriacao());
			contaDto.setContas(conta.getContas());
			
			listaConta.add(contaDto);
		}
		
		return listaConta;
	}

	@Override
	public ContaDto consultarPeloId(Long id) {
		
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

	@Override
	public void realizarTransferencia(HistoricoDto historicoDto) throws RegraNegocioException {
		
		realizarValidacoes(historicoDto);
		
		realizarTransferenciaContas(historicoDto);
		
	}
	
	private void realizarTransferenciaContas(HistoricoDto historicoDto) throws RegraNegocioException {
		
		Conta contaOrigem = historicoDto.getContaOrigem();
		Conta contaDestino = historicoDto.getContaDestino();
		BigDecimal valorTransferencia = historicoDto.getValor();
		
		
		switch (TipoTransacao.recuperarEnum(historicoDto.getTipoTransferencia())) {
		
			case APORTE:
				
				//Adicionando o valor a ser transferido para a Conta de Destino
				contaDestino.setSaldo(contaDestino.getSaldo().add(valorTransferencia));
				
				repository.save(contaDestino);
				
				break;
			
			case TRANFERENCIA:
				
				//Subtraindo o valor a ser transferido da Conta de Origem
				contaOrigem.getSaldo().min(valorTransferencia);
				
				//Adicionando o valor a ser transferido para a Conta de Destino
				contaDestino.setSaldo(contaDestino.getSaldo().add(valorTransferencia));
				
				repository.save(contaOrigem);
				repository.save(contaDestino);
				
				break;
			
			default:
				break;
		}
		
	}

	private void realizarValidacoes(HistoricoDto historicoDto) throws RegraNegocioException {
		
		Conta contaOrigem = historicoDto.getContaOrigem();
		Conta contaDestino = historicoDto.getContaDestino();
		
		switch (TipoTransacao.recuperarEnum(historicoDto.getTipoTransferencia())) {
		
			case APORTE:
				
				if(contaDestino.getTipoConta() == TipoConta.FILIAL) {
					throw new RegraNegocioException("Não é permitido realizar aporte para Conta Filial");
				}
				
				break;
			
			case TRANFERENCIA:
				
				if(contaOrigem == null) {
					throw new RegraNegocioException("Conta de Origem não pode ser vazia");
				}
				
				if(contaDestino.getTipoConta() == TipoConta.MATRIZ) {
					throw new RegraNegocioException("Não é permitido realizar transferencia para Conta Matriz");
				}
				
				if(contaOrigem.getId() != contaDestino.getIdPai()) {
					throw new RegraNegocioException("Não é permitido realizar transferencia de uma conta Filial que não pertença a Conta Matriz correspondente");
				}
				
				if(contaOrigem.getSituacao() == SituacaoConta.BLOQUEADA || 
						contaOrigem.getSituacao() == SituacaoConta.CANCELADA) {
					throw new RegraNegocioException("Não é permitido realizar transferencia devido a conta de origem estar Bloqueada e/ou Cancelada");
				}
				
				break;
			
			case TRANSACAO_INVALIDA:
			default:
				throw new RegraNegocioException("API somente para realizar Transferencia e/ou Aporte");
			
		}
		
		if(contaDestino.getSituacao() == SituacaoConta.BLOQUEADA || 
				contaDestino.getSituacao() == SituacaoConta.CANCELADA) {
			throw new RegraNegocioException("Não é permitido realizar transferencia devido a conta de destino estar Bloqueada e/ou Cancelada");
		}
		
	}
	
	@Override
	public void realizarAporte(HistoricoDto historicoDto) throws RegraNegocioException {
		
		Conta contaDestino = historicoDto.getContaDestino();
		BigDecimal valorTransferencia = historicoDto.getValor();
		
		if(historicoDto.getTipoTransferencia().equals(TipoTransacao.APORTE.name())) {
			throw new RegraNegocioException("API somente para realizar Transferencia");
		}
		
		if(contaDestino.getTipoConta() == TipoConta.MATRIZ) {
			throw new RegraNegocioException("Não é permitido realizar transferencia para Conta Matriz");
		}
		
		if(contaDestino.getSituacao() == SituacaoConta.BLOQUEADA || 
				contaDestino.getSituacao() == SituacaoConta.CANCELADA) {
			throw new RegraNegocioException("Não é permitido realizar transferencia devido a conta de destino estar Bloqueada e/ou Cancelada");
		}
		
		//Adicionando o valor a ser transferido para a Conta de Destino
		contaDestino.setSaldo(contaDestino.getSaldo().add(valorTransferencia));
		
		repository.save(contaDestino);
	}

	@Override
	public void realizarEstorno(HistoricoDto historicoDto) throws RegraNegocioException {
		
		realizarValidacoes(historicoDto);
		
		realizarEstornoContas(historicoDto);
		
	}

	private void realizarEstornoContas(HistoricoDto historicoDto) {
		
		Conta contaOrigem = historicoDto.getContaOrigem();
		Conta contaDestino = historicoDto.getContaDestino();
		BigDecimal valorTransferencia = historicoDto.getValor();
		
		
		switch (TipoTransacao.recuperarEnum(historicoDto.getTipoTransferencia())) {
		
			case APORTE:
				
				//Adicionando o valor a ser transferido para a Conta de Destino
				contaDestino.setSaldo(contaDestino.getSaldo().min(valorTransferencia));
				
				repository.save(contaDestino);
				
				break;
			
			case TRANFERENCIA:
				
				//Subtraindo o valor a ser transferido da Conta de Origem
				contaOrigem.getSaldo().add(valorTransferencia);
				
				//Adicionando o valor a ser transferido para a Conta de Destino
				contaDestino.setSaldo(contaDestino.getSaldo().min(valorTransferencia));
				
				repository.save(contaOrigem);
				repository.save(contaDestino);
				
				break;
			
			default:
				break;
		}
		
	}

}