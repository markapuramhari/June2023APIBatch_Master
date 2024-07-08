package putAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.user.api.UserLombok;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RA_07_UpdateUserTest {
	
	public static String getRandomEmailId() {
		return "apiAutomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	

	@Test(priority = 1)
	public void updateUserTest() {
		
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
		
		System.out.println("------UPDATE-------");
		
//PUT call
		
		//update the existing user using Setters
		user.setName("Babu");
		user.setStatus("inactive");
		
		
		given()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
				.body(user) //Serialization
			
			.when()
				.put("/public/v2/users/"+userid)
				.then()
				.log().all()
				.assertThat()
				.statusCode(200)
				.body("id", equalTo(userid))
				.body("name", equalTo(user.getName()))
				.body("status", equalTo(user.getStatus()));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
