package postAPIs;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RA_03_BookingAuthTest {

	
	@Test(priority = 1)
	public void getBookingAuthToken_with_JSON_String_Test() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		
		String  tokenId= given()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "\"username\": \"admin\",\r\n"
					+ "\"password\": \"password123\"\r\n"
					+ "\r\n"
					+ "}")
		.when()
			.post("/auth")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("token");
		
		
		System.out.println("Token: "+tokenId);
		Assert.assertNotNull(tokenId);
		
		
	}
	
	@Test(priority = 2)
	public void getBookingAuthToken_with_JSON_FileTest() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		
		String  tokenId= given()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/basicAuth.json"))
		.when()
			.post("/auth")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("token");
		
		
		System.out.println("Token: "+tokenId);
		Assert.assertNotNull(tokenId);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
