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
	public Long cadastrarOuAtualizar(PessoaDto pessoaDto) {
		Pessoa entity = new Pessoa();
		
		if(pessoaDto.getId() != null) {
			
			entity = repository.findById(pessoaDto.getId()).get();
		}
		
		entity.setNome(pessoaDto.getNome());
		entity.setDataNascimento(pessoaDto.getDataNascimento());
		entity.setNumeroCpfCnpj(pessoaDto.getNumeroCpfCnpj());
		entity.setRazaoSocial(pessoaDto.getRazaoSocial());
		entity.setTipoPessoa(TipoPessoa.recuperarEnum(pessoaDto.getTipoPessoa()));		
		
		repository.save(entity);
		
		return entity.getId();
	}

	@Override
	public List<PessoaDto> consultarTodos() {
		List<PessoaDto> listaPessoa = new ArrayList<PessoaDto>();
		
		for (Pessoa pessoa : repository.findAll()) {
			PessoaDto pessoaDto = new PessoaDto(); 
			
			pessoaDto.setId(pessoa.getId());
			pessoaDto.setNome(pessoa.getNome());
			pessoaDto.setDataNascimento(pessoa.getDataNascimento());
			pessoaDto.setNumeroCpfCnpj(pessoa.getNumeroCpfCnpj());
			pessoaDto.setRazaoSocial(pessoa.getRazaoSocial());
			pessoaDto.setTipoPessoa(pessoa.getTipoPessoa().name());
			
			listaPessoa.add(pessoaDto);
		}
		
		return listaPessoa;
	}

	@Override
	public PessoaDto consultarPeloId(Long id) {
		
		PessoaDto pessoaDto = new PessoaDto();
		
		Pessoa pessoa = repository.findById(id).get();
		
		pessoaDto.setId(pessoa.getId());
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setDataNascimento(pessoa.getDataNascimento());
		pessoaDto.setNumeroCpfCnpj(pessoa.getNumeroCpfCnpj());
		pessoaDto.setRazaoSocial(pessoa.getRazaoSocial());
		pessoaDto.setTipoPessoa(pessoa.getTipoPessoa().name());
		
		return pessoaDto;
	}

	@Override
	public void deletar(Long id) {
		
		repository.deleteById(id);
		
	}

}
