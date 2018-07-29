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

import com.hubfintech.app.dtos.ContaDto;
import com.hubfintech.app.responses.Response;
import com.hubfintech.app.services.ContaService;

@RestController
@RequestMapping("/api/contas")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Response<ContaDto>> cadastrar(@Valid @RequestBody ContaDto contaDto, BindingResult result) {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			response.setData(Arrays.asList(contaService.cadastrarOuAtualizar(contaDto)));
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	@CrossOrigin
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<ContaDto>> recuperarPeloId(@PathVariable("id") Long id) {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			response.setData(Arrays.asList(contaService.recuperarPeloId(id)));
			
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
	@PutMapping
	public ResponseEntity<Response<ContaDto>> atualizar(@Valid @RequestBody ContaDto contaDto, BindingResult result) {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			response.setData(Arrays.asList(contaService.cadastrarOuAtualizar(contaDto)));
			
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
	@GetMapping
	public ResponseEntity<Response<ContaDto>> recuperar() {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			response.setData(contaService.recuperarTodos());
			
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
			
			contaService.recuperarPeloId(id);
			
			contaService.deletar(id);
			
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
