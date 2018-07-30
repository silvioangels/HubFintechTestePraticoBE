package com.hubfintech.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping
	public ResponseEntity<Response<HistoricoDto>> consultar() {
		
		Response<HistoricoDto> response = new Response<HistoricoDto>();
		
		try {
			
			response.setData(historicoService.consultarTodos());
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

}