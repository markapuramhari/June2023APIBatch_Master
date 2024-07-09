package _07_com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import _06_com.product.api.ProductPOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RA_06_CreateUserTestWithLombok {
	
	public static String getRandomEmailId() {
		return "apiAutomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	

	@Test(priority = 1)
	public void createUserTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		UserLombok user=new UserLombok("Hari", getRandomEmailId(), "male", "active");
		
		Response response= given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
			.body(user) //Serialization
		
		.when()
			.post("/public/v2/users");
			
		Integer userid= response.jsonPath().get("id");
		System.out.println("userid: "+userid);
		
		
//GET API to get the same user
		
		
		Response getResponse= given()
			.log().all()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/"+userid);
		
		System.out.println("-----------");
		
//De-Serialization
		
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			UserLombok userRes= mapper.readValue(getResponse.getBody().asString(),UserLombok.class); //De-Serialization
			
			
			System.out.println(userRes.getId());
			System.out.println(userRes.getEmail());
			System.out.println(userRes.getStatus());
			System.out.println(userRes.getGender());
			
			Assert.assertEquals(userid, userRes.getId());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(user.getEmail(), userRes.getEmail());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test(priority = 2)
	public void createUser_BuilderPatternTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		
		UserLombok user= new UserLombok.UserLombokBuilder()
				.name("Hari")
				.email(getRandomEmailId())
				.status("active")
				.gender("male")
				.build();
		
		
		
		Response response= given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
			.body(user) //Serialization
		
		.when()
			.post("/public/v2/users");
			
		Integer userid= response.jsonPath().get("id");
		System.out.println("userid: "+userid);
		
		
//GET API to get the same user
		
		
		Response getResponse= given()
			.log().all()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/"+userid);
		
		System.out.println("-----------");
		
//De-Serialization
		
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			UserLombok userRes= mapper.readValue(getResponse.getBody().asString(),UserLombok.class); //De-Serialization
			
			
			System.out.println(userRes.getId());
			System.out.println(userRes.getEmail());
			System.out.println(userRes.getStatus());
			System.out.println(userRes.getGender());
			
			Assert.assertEquals(userid, userRes.getId());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(user.getEmail(), userRes.getEmail());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
