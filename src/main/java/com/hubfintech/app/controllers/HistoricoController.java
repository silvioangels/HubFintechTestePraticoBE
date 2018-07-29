package com.hubfintech.app.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubfintech.app.dtos.HistoricoDto;
import com.hubfintech.app.responses.Response;
import com.hubfintech.app.services.HistoricoService;

@RestController
@RequestMapping("/api/historicos")
public class HistoricoController {
	
	@Autowired
	private HistoricoService historicoService;
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Response<HistoricoDto>> cadastrar(@Valid @RequestBody HistoricoDto historicoDto, BindingResult result) {
		
		Response<HistoricoDto> response = new Response<HistoricoDto>();
		
		try {
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			response.setData(Arrays.asList(historicoService.cadastrarOuAtualizar(historicoDto)));
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<Response<HistoricoDto>> recuperar() {
		
		Response<HistoricoDto> response = new Response<HistoricoDto>();
		
		try {
			
			response.setData(historicoService.recuperarTodos());
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

}
