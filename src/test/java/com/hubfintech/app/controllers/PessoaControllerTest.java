package com.hubfintech.app.controllers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hubfintech.app.conf.ConfigurationsTest;
import com.hubfintech.app.dtos.PessoaDto;
import com.hubfintech.app.entities.Pessoa;
import com.hubfintech.app.enums.TipoPessoa;
import com.hubfintech.app.responses.Response;

@SuppressWarnings("rawtypes")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PessoaControllerTest extends ConfigurationsTest{
	
	@Test
    public void teste1ConsultarPessoas() {
		
        given().when().get("/pessoas").then().statusCode(200);
        
    }
	
	@Test
	public void teste2CadastrarPessoa() {
		Gson gson = new Gson();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste");
		pessoa.setNumeroCpfCnpj("129.051.428-39");
		pessoa.setTipoPessoa(TipoPessoa.FISICA);
		
		given()
        .contentType("application/json")
        .body(gson.toJson(pessoa))
        .when().post("/pessoas").then()
        .body("data[0].id",is(notNullValue()))
        ;
        
	}
	
	@Test
	public void teste3AtualizarPessoa() {
		
		Response response = given().when().get("/pessoas").as(Response.class);
		List<PessoaDto> listaPessoaResponse = recuperarDto(response);
		
		Gson gson = new Gson();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(listaPessoaResponse.get(0).getId());
		pessoa.setNome("Teste");
		pessoa.setNumeroCpfCnpj("129.051.428-39");
		pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
		
		given()
        .contentType("application/json")
        .body(gson.toJson(pessoa))
        .when().put("/pessoas").then()
        .body("data[0].tipoPessoa", equalTo("JURIDICA"))
        .statusCode(200)
        ;
        
	}
	
	@Test
	public void teste4DeletarPessoa() {
		
		Response response = given().when().get("/pessoas").as(Response.class);
		List<PessoaDto> listaPessoaResponse = recuperarDto(response);
		
		given()
        .contentType("application/json")
        .when().delete("/pessoas/"+listaPessoaResponse.get(0).getId()).then()
        .statusCode(200)
        ;
        
	}
	
	private List<PessoaDto> recuperarDto(Response response) {
		List<PessoaDto> lista = new ArrayList<PessoaDto>();
		Gson gson = new Gson();
		
		for (int i = 0; i < response.getData().size(); i++) {
			
			LinkedHashMap map = (LinkedHashMap)response.getData().get(i);
			JsonElement jsonElement = gson.toJsonTree(map);
			lista.add(gson.fromJson(jsonElement, PessoaDto.class));
		}
		
        return lista;
		
	}
	
}
