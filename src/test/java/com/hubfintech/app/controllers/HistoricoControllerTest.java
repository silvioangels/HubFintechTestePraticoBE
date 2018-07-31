package com.hubfintech.app.controllers;

import static com.jayway.restassured.RestAssured.given;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hubfintech.app.conf.ConfigurationsTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HistoricoControllerTest extends ConfigurationsTest{
	
	@Test
    public void teste1ConsultarHistorico() {
		
        given().when().get("/historicos").then().statusCode(200);
        
    }
	
}