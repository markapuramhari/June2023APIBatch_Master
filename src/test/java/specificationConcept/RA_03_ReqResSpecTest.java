package specificationConcept;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RA_03_ReqResSpecTest {
	
	public static RequestSpecification user_req_spec() {
			
			RequestSpecification requestSpec=	 new RequestSpecBuilder()
					.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
					.addHeader("Authorization", "Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
					.build();
			
			return requestSpec;
			
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



	@Test
	public void getUser_with_Req_Res_Spec_Test() {
		
		
		given()
			.log().all()
			.spec(user_req_spec())
		.when()
			.get("/public/v2/users")
		
		.then()
			.log().all()
			.spec(get_res_spec_200_OK_with_Body());
		
		
		
		
	}



}
