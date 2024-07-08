package authAPIs;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class RA_08_AuthTest {
	
	@Test(priority = 1)
	public void jwtAuthTest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		
		String JWTToken= given()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "            \"username\": \"mor_2314\",\r\n"
					+ "                \"password\": \"83r5^_\"\r\n"
					+ "}")
		.when()
			.post("/auth/login")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().path("token");
		
		System.out.println("JWTToken: "+JWTToken);
		
		String tokenArr[]= JWTToken.split("\\.");
		
		System.out.println("token: "+tokenArr[0]);
		System.out.println("token: "+tokenArr[1]);
		System.out.println("token: "+tokenArr[2]);
			
	}
	
	
	
	@Test(priority = 2)
	public void basicAuthTest() {
		
		RestAssured.baseURI="https://the-internet.herokuapp.com";
		
		String responseBody= given()
				.auth().basic("admin", "admin")
		.when()
			.get("/basic_auth")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().body().asString();
		
		System.out.println("responseBody: "+responseBody.contains("Congratulations"));
				
	}
	
	
	@Test(priority = 3)
	public void preemptiveAuthTest() {
		
		RestAssured.baseURI="https://the-internet.herokuapp.com";
		
		String responseBody= given()
				.auth().preemptive().basic("admin", "admin") //encoding
		.when()
			.get("/basic_auth")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().body().asString();
		
		System.out.println("responseBody: "+responseBody.contains("Congratulations"));
				
	}
	
	
	@Test(priority = 4)
	public void digestAuthTest() {
		
		RestAssured.baseURI="https://the-internet.herokuapp.com";
		
		String responseBody= given()
				.auth().digest("admin", "admin") //more secure
		.when()
			.get("/basic_auth")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().body().asString();
		
		System.out.println("responseBody: "+responseBody.contains("Congratulations"));
				
	}
	
	
	@Test(priority = 5)
	public void getAccessToken() {
		
//get access token-POST
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		 String accessToken= given()
			.header("Content-type","application/x-www-form-urlencoded")
			.formParam("grant_type", "client_credentials")
			.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
			.formParam("client_secret", "VjjgfcJilNAzcSJw")
		.when()
			.post("/v1/security/oauth2/token")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("access_token");
		
		System.out.println(accessToken);
		
		
	}
	
	
	@Test(priority = 6)
	public void apiKeyAuthTest() {
		
		RestAssured.baseURI="api.weatherapi.com";
		
		String responseBody= given()
				.queryParam("q", "London")
				.queryParam("aqi", "no")
				.queryParam("key", "0b7567ac66234b9daba104729230806")
		.when()
			.get("/v1/current.json")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract().body().asString();
		
		System.out.println("responseBody: "+responseBody.contains("Congratulations"));
				
	}
	
	
	
	

}
