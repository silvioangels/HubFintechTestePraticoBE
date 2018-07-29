package com.hubfintech.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubfintech.app.dtos.PessoaDto;
import com.hubfintech.app.entities.Pessoa;
import com.hubfintech.app.enums.TipoPessoa;
import com.hubfintech.app.repositories.PessoaRepository;
import com.hubfintech.app.services.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaRepository repository;
	
	@Override
	public void cadastrarOuAtualizar(PessoaDto contaDto) {
		
		Pessoa entity = new Pessoa();
		
		entity.setNome(contaDto.getNome());
		entity.setConta(contaDto.getConta());
		entity.setDataNascimento(contaDto.getDataNascimento());
		entity.setNumeroCpfCnpj(contaDto.getNumeroCpfCnpj());
		entity.setRazaoSocial(contaDto.getRazaoSocial());
		entity.setTipoPessoa(TipoPessoa.recuperarEnum(contaDto.getTipoPessoa()));
		
		repository.save(entity);
		
	}

	@Override
	public List<PessoaDto> recuperarTodos() {
		List<PessoaDto> listaPessoa = new ArrayList<PessoaDto>();
		
		for (Pessoa pessoa : repository.findAll()) {
			PessoaDto pessoaDto = new PessoaDto(); 
			
			pessoaDto.setId(pessoa.getId());
			pessoaDto.setNome(pessoa.getNome());
			pessoaDto.setDataNascimento(pessoa.getDataNascimento());
			pessoaDto.setNumeroCpfCnpj(pessoa.getNumeroCpfCnpj());
			pessoaDto.setRazaoSocial(pessoa.getRazaoSocial());
			pessoaDto.setTipoPessoa(pessoa.getTipoPessoa().name());
			pessoaDto.setConta(pessoa.getConta());
			
			listaPessoa.add(pessoaDto);
		}
		
		return listaPessoa;
	}

	@Override
	public PessoaDto recuperarPeloId(Long id) {
		
		PessoaDto pessoaDto = new PessoaDto();
		
		Pessoa pessoa = repository.findById(id).get();
		
		pessoaDto.setId(pessoa.getId());
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setDataNascimento(pessoa.getDataNascimento());
		pessoaDto.setNumeroCpfCnpj(pessoa.getNumeroCpfCnpj());
		pessoaDto.setRazaoSocial(pessoa.getRazaoSocial());
		pessoaDto.setTipoPessoa(pessoa.getTipoPessoa().name());
		pessoaDto.setConta(pessoa.getConta());
		
		return pessoaDto;
	}

	@Override
	public void deletar(Long id) {
		
		repository.deleteById(id);
		
	}

}
