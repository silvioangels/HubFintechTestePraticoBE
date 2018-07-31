package com.hubfintech.app.controllers;

import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubfintech.app.dtos.PessoaDto;
import com.hubfintech.app.responses.Response;
import com.hubfintech.app.services.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<Response<PessoaDto>> consultar() {
		
		Response<PessoaDto> response = new Response<PessoaDto>();
		
		try {
			
			response.setData(pessoaService.consultarTodos());
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@CrossOrigin
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<PessoaDto>> consultarPeloId(@PathVariable("id") Long id) {
		
		Response<PessoaDto> response = new Response<PessoaDto>();
		
		try {
			
			response.setData(Arrays.asList(pessoaService.consultarPeloId(id)));
			
		} catch (NoSuchElementException e) {
			response.getErrors().add("Registro não encontrado.");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Response<PessoaDto>> cadastrar(@Valid @RequestBody PessoaDto pessoaDto, BindingResult result) {
		
		Response<PessoaDto> response = new Response<PessoaDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Long id = pessoaService.cadastrarOuAtualizar(pessoaDto);
			response.setData(Arrays.asList(pessoaService.consultarPeloId(id)));
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	@CrossOrigin
	@PutMapping
	public ResponseEntity<Response<PessoaDto>> atualizar(@Valid @RequestBody PessoaDto contaDto, BindingResult result) {
		
		Response<PessoaDto> response = new Response<PessoaDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Long id = pessoaService.cadastrarOuAtualizar(contaDto);
			response.setData(Arrays.asList(pessoaService.consultarPeloId(id)));
			
		} catch (NoSuchElementException e) {
			response.getErrors().add("Registro não encontrado.");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	
	
	@CrossOrigin
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id) {
		
		Response<String> response = new Response<String>();
		
		try {
			
			pessoaService.consultarPeloId(id);
			
			pessoaService.deletar(id);
			
		} catch (DataIntegrityViolationException e) {
			response.getErrors().add("Não é possivel apagar o registro filho.");
			return ResponseEntity.badRequest().body(response);
		} catch (NoSuchElementException e) {
			response.getErrors().add("Registro não encontrado.");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}		
		
		return ResponseEntity.ok(response);
	}

}