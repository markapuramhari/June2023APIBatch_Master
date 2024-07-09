package _04_postAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class RA_04_BookingAuthWithPojoTest {
	
	//POJO  - plain old Java Object
	//not extend any other class
	//encapsulation getter and setter 
	//private class variable --json body
	
	
	
	@Test
	public void getBookingAuthToken_with_JSON_String_Test() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Credentials creds=new Credentials("admin", "password123");
		
		
		String  tokenId= given()
			.log().all()	
			.contentType(ContentType.JSON)
			.body(creds)
		.when()
			.post("/auth")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200).extract().path("token");
		
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
		
		
	}
	
	

}
