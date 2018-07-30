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
import com.hubfintech.app.dtos.HistoricoDto;
import com.hubfintech.app.exception.RegraNegocioException;
import com.hubfintech.app.responses.Response;
import com.hubfintech.app.services.ContaService;
import com.hubfintech.app.services.HistoricoService;

@RestController
@RequestMapping("/api/contas")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private HistoricoService historicoService;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<Response<ContaDto>> consultar() {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			response.setData(contaService.consultarTodos());
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@CrossOrigin
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<ContaDto>> consultarPeloId(@PathVariable("id") Long id) {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			response.setData(Arrays.asList(contaService.consultarPeloId(id)));
			
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
	public ResponseEntity<Response<ContaDto>> cadastrar(@Valid @RequestBody ContaDto contaDto, BindingResult result) {
		
		Response<ContaDto> response = new Response<ContaDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Long id = contaService.cadastrarOuAtualizar(contaDto);
			response.setData(Arrays.asList(contaService.consultarPeloId(id)));
			
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
			
			Long id = contaService.cadastrarOuAtualizar(contaDto);
			response.setData(Arrays.asList(contaService.consultarPeloId(id)));
			
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
			
			contaService.consultarPeloId(id);
			
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
	
	@CrossOrigin
	@PostMapping("/transferencia")
	public ResponseEntity<Response<HistoricoDto>> realizarTransferencia(@Valid @RequestBody HistoricoDto historicoDto, BindingResult result) {
		
		Response<HistoricoDto> response = new Response<HistoricoDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			//chama o serviço que realiza a transferencia
			contaService.realizarTransferencia(historicoDto);
			
			//chama o servico que grava o historico
			Long id = historicoService.cadastrarOuAtualizar(historicoDto);
			response.setData(Arrays.asList(historicoService.consultarPeloId(id)));
			
		} catch (RegraNegocioException e) {
			response.getErrors().add("Regra de Negocio: " +e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	@CrossOrigin
	@DeleteMapping("/transferencia/{id}")
	public ResponseEntity<Response<String>> realizarEstorno(@PathVariable("id") Long id) {
		
		Response<String> response = new Response<String>();
		
		try {
			
			HistoricoDto historicoDto = historicoService.consultarPeloId(id);
			
			contaService.realizarEstorno(historicoDto);
			
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
