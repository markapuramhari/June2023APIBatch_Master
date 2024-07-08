package schemaValidation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class RA_07_SchemaValidationTest {
	
	@Test(priority = 1)
	public void getOneUserAPISchemaValidationTest() {
		
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
	.when()
		.get("/public/v2/users/7017131")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);
		
		//.body(matchesJsonSchemaInClasspath("createuserschema.json"));
		
	}
	
	
	@Test(priority = 2)
	public void getAllUserAPISchemaValidationTest() {
		
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
	.when()
		.get("/public/v2/users/")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);
		//.body(matchesJsonSchemaInClasspath("getallUsersSchema.json"));
		
	}

}
