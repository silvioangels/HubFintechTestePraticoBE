package com.hubfintech.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

	@Test
	public void validarDocumentacaoSwagger() {
		given().when().get("http://localhost:8088/swagger-ui.html").then().statusCode(200);
	}
	
}
