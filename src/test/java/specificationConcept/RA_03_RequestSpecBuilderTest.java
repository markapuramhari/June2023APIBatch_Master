package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RA_03_RequestSpecBuilderTest {
	
	
	
	public static RequestSpecification user_req_spec() {
		
		RequestSpecification requestSpec=	 new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
				.build();
		
		return requestSpec;
		
	}
	
	
	
	@Test(priority = 1)
	public void getUser_with_Request_Spec() {
			
		given()
			.spec(user_req_spec())
		.when()
			.get("/public/v2/users")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200);	
	}
	
	@Test(priority = 2)
	public void getUser_with_param_Request_Spec() {
		
		given()
			.queryParam("name", "naveen")
			.queryParam("status", "active")
			.spec(user_req_spec())
		
		.when()
			.get("/public/v2/users")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("[0].name", equalTo("Naveen Trivedi"));
	}
	
	
	
	
	
	
	
	
	
	

}
