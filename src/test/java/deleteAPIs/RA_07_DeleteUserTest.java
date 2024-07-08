package deleteAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.user.api.UserLombok;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class RA_07_DeleteUserTest {
	
	public static String getRandomEmailId() {
		return "apiAutomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	

	@Test(priority = 1)
	public void deleteUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		UserLombok user=new UserLombok("Hari", getRandomEmailId(), "male", "active");
		
			
//POST call
		
		Response response= given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
			.body(user) //Serialization
		
		.when()
			.post("/public/v2/users");
			
		Integer userid= response.jsonPath().get("id");
		System.out.println("userid: "+userid);
		
		System.out.println("--------DELETE-----------");
		
		
		
//Delete User
		
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
	.when()
		.delete("/public/v2/users/"+userid)
		.then()
		.log().all()
		.assertThat()
		.statusCode(204);
		
		
//GET  //404
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
	.when()
		.get("/public/v2/users/"+userid)
		.then()
		.log().all()
		.assertThat()
		.statusCode(404)
		.body("message", equalTo("Resource not found"));
		
		
		
		
		
		
		
	}

}
