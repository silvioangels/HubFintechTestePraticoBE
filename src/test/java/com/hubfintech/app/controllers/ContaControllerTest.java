package com.hubfintech.app.controllers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hubfintech.app.conf.ConfigurationsTest;
import com.hubfintech.app.dtos.ContaDto;
import com.hubfintech.app.entities.Conta;
import com.hubfintech.app.entities.Pessoa;
import com.hubfintech.app.enums.SituacaoConta;
import com.hubfintech.app.enums.TipoConta;
import com.hubfintech.app.enums.TipoPessoa;
import com.hubfintech.app.responses.Response;

@SuppressWarnings("rawtypes")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaControllerTest extends ConfigurationsTest{
	
	@Test
    public void teste1ConsultarContas() {
        
		given().when().get("/contas").then().statusCode(200);
        
    }
	
	@Test
	public void teste2CadastrarConta() {
		Gson gson = new Gson();
		
		Conta conta = new Conta();
		conta.setNome("Teste 1");
		conta.setSaldo(new BigDecimal(1000));
		conta.setSituacao(SituacaoConta.ATIVA);
		conta.setTipoConta(TipoConta.MATRIZ);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste");
		pessoa.setNumeroCpfCnpj("129.051.428-39");
		pessoa.setTipoPessoa(TipoPessoa.FISICA);
		conta.setPessoa(pessoa);
		
		given()
        .contentType("application/json")
        .body(gson.toJson(conta))
        .when().post("/contas").then()
        .body("data[0].id",is(notNullValue()))
        ;
        
	}
	
	@Test
	public void teste3AtualizarConta() {
		
		Response response = given().when().get("/contas").as(Response.class);
		ContaDto contaResponse = recuperarDto(response); 
		
		Gson gson = new Gson();
		
		Conta conta = new Conta();
		conta.setId(contaResponse.getId());
		conta.setNome("Teste 1");
		conta.setSaldo(new BigDecimal(1500));
		conta.setSituacao(SituacaoConta.ATIVA);
		conta.setTipoConta(TipoConta.MATRIZ);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste");
		pessoa.setNumeroCpfCnpj("129.051.428-39");
		pessoa.setTipoPessoa(TipoPessoa.FISICA);
		conta.setPessoa(pessoa);
		
		given()
        .contentType("application/json")
        .body(gson.toJson(conta))
        .when().put("/contas").then()
        .body("data[0].saldo", equalTo(1500))
        .statusCode(200)
        ;
        
	}
	
	@Test
	public void teste4DeletarConta() {
		
		Response response = given().when().get("/contas").as(Response.class);
		ContaDto contaResponse = this.recuperarDto(response); 
		
		given()
        .contentType("application/json")
        .when().delete("/contas/"+contaResponse.getId()).then()
        .statusCode(200)
        ;
        
	}
	
	private ContaDto recuperarDto(Response response) {
		Gson gson = new Gson();
		LinkedHashMap map = (LinkedHashMap)response.getData().get(0);
        JsonElement jsonElement = gson.toJsonTree(map);
        return gson.fromJson(jsonElement, ContaDto.class);
		
	}
	
}
