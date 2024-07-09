package _04_postAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RA_03_CreateUserTest {
	
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
//1. add User -POST
		
		int UserId= given()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/addUser.json"))
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.post("/public/v2/users/")
		.then()
			.log().all()
			.assertThat()
			.statusCode(201)
			.body("email", equalTo("mmm@gmail.com"))
			.extract()
			.path("id");
		
		System.out.println(UserId);
		Assert.assertNotNull(UserId);
		
//2. get the same user and verify it: GET
		
		
		given()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/"+UserId)
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("id", equalTo(UserId));
		

		
	}
	
	

}
