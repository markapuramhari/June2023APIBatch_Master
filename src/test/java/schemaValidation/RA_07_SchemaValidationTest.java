package schemaValidation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class RA_07_SchemaValidationTest {
	
	@Test(priority = 1)
	public void getOneUserAPISchemaValidationTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
	.when()
		.get("/public/v2/users/7017684")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createuserschema.json"));
		
		System.out.println("---------------");
	}
	
	



	@Test(priority = 2)
	public void getAllUserAPISchemaValidationTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
	.when()
		.get("/public/v2/users/")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getallUsersSchema.json"));
		
	}

}
