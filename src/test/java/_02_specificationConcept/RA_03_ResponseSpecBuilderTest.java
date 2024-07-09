package _02_specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RA_03_ResponseSpecBuilderTest {
	
	
	public static ResponseSpecification get_res_spec_200_OK() {
		
		ResponseSpecification res_spec_200_ok= new ResponseSpecBuilder()		
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.build();
		
		return res_spec_200_ok;
	}
	
	public static ResponseSpecification get_res_spec_401_Auth_Fail() {
		
		ResponseSpecification res_spec_401_Auth_Fail= new ResponseSpecBuilder()		
		.expectStatusCode(401)
		.expectHeader("Server", "cloudflare")
		.build();
		
		return res_spec_401_Auth_Fail;
	}
	
	public static ResponseSpecification get_res_spec_200_OK_with_Body() {
		
		ResponseSpecification res_spec_200_ok_with_Body= new ResponseSpecBuilder()		
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.expectBody("$.size()",equalTo(10))
		.expectBody("id",hasSize(10))
		.build();
		
		return res_spec_200_ok_with_Body;
	}
	
	
	
	
	@Test(priority = 1)
	public void get_user_res_200_spec_Test() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		
		given()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users")
		.then()
			.log().all()
			.assertThat()
			.spec(get_res_spec_200_OK())
			.spec(get_res_spec_200_OK_with_Body());
			
	}
	
	
	
	@Test(priority = 2)
	public void get_user_res_401_Auth__Fail_Test() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		
		given()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d11")
		.when()
			.get("/public/v2/users")
		.then()
			.log().all()
			.assertThat()
			.spec(get_res_spec_401_Auth_Fail());
			
	}
	

}
