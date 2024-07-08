package postAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class RA_04_CreateUserWithPOJOTest {

	
	//1. direct supply the json string
	//2. pass the json file
	//3. POJO (jackson/rest assured)
	
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
		
	}
	
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		User user=new User("Hari",  "male",getRandomEmailId(), "active");
		
//1. add User -POST
		
		int UserId= given()
			.log().all()	
			.contentType(ContentType.JSON)
			.body(user)
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.post("/public/v2/users/")
		.then()
			.log().all()
			.assertThat()
			.statusCode(201)
			.body("name", equalTo(user.getName()))
			.extract()
			.path("id");
		
		System.out.println(UserId);
		Assert.assertNotNull(UserId);
		
//2. get the same user and verify it: GET
		
		System.out.println("------------------");
		given()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/"+UserId)
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("id", equalTo(UserId))
			.body("name", equalTo(user.getName()))
			.body("email", equalTo(user.getEmail()))
			.body("gender", equalTo(user.getGender()))
			.body("status", equalTo(user.getStatus()));
			
	}
	
	
	
	
	
}
