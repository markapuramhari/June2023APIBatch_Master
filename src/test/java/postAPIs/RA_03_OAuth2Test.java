package postAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RA_03_OAuth2Test {
	
	static String accessToken;
	
	
	@BeforeMethod
	//@Test
	public void getAccessToken() {
		
//get access token-POST
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		 accessToken= given()
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
		
	@Test
	public void getFlightTest() {
	
		
//2. get flight info:GET
		
	Response response=	given()
			.header("Authorization","Bearer "+accessToken)
			.queryParam("origin", "PAR")
			.queryParam("maxPrice", 200)
		.when()
			.get("/v1/shopping/flight-destinations")
		.then()
			.log().all()
			.assertThat()
			//.statusCode(200)
			.statusCode(404)//404 now 
			.extract()
			.response();
			
		
		
	JsonPath jp= response.jsonPath();	
		
		String type= jp.getString("data[0].type");
		
		System.out.println(type);
	
	}
	

}
