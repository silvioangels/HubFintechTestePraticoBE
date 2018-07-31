package com.hubfintech.app.controllers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.google.gson.Gson;
import com.hubfintech.app.conf.ConfigurationsTest;
import com.hubfintech.app.entities.Conta;
import com.hubfintech.app.enums.SituacaoConta;
import com.hubfintech.app.enums.TipoConta;

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
		
		given()
        .contentType("application/json")
        .body(gson.toJson(conta))
        .when().post("/contas").then()
        .body("data[0].id",is(notNullValue()))
        ;
        
	}
	
	@Test
	public void teste3AtualizarConta() {
		
		Gson gson = new Gson();
		
		Conta conta = new Conta();
		conta.setId(1L);
		conta.setNome("Teste 1");
		conta.setSaldo(new BigDecimal(1500));
		conta.setSituacao(SituacaoConta.ATIVA);
		conta.setTipoConta(TipoConta.MATRIZ);
		
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
		
		given()
        .contentType("application/json")
        .when().delete("/contas/1").then()
        .statusCode(200)
        ;
        
	}
	
}
