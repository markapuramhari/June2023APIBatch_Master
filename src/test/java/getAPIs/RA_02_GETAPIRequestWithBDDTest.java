package getAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class RA_02_GETAPIRequestWithBDDTest {
	
	
	@BeforeMethod
	public void allureSetup() {
		
		RestAssured.filters(new AllureRestAssured());
	}
	
	
	@Test(priority = 1)
	public void getProductsTest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
			
		given()
			.log().all()
		.when()
			.get("/products")
		
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.header("Connection", "keep-alive")
		.and()
			.body("$.size()",equalTo(20))
		.and()
			.body("id", is(notNullValue())) 
			.body("title", hasItem("Mens Cotton Jacket")) //any title validate in Response
			.body("[2].title", equalTo("Mens Cotton Jacket"));//spcific title validate in Response
		System.out.println("-----------------------");
	}
	
	
	
	@Test(priority = 2)
	public void getUserAPITest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
			.log().all()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("$.size()", equalTo(10))
			.body("status", hasItem("active")); //or //any name validate in Response
			//.body("[3].name", equalTo("Rageswari Iyer DVM")); //spcific name validate in Response
			
			System.out.println("------------------");	
	}
	
	@Test(priority = 3)
	public void getProductDataAPIWithQueryParamTest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		given()
			.log().all()
			.queryParam("limit", 5)
		
		.when()
			.get("/products")	
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("$.size()", equalTo(5))
			.body("[1].rating.rate", equalTo(4.1F))
			.body("[1].title", equalTo("Mens Casual Premium Slim Fit T-Shirts "));
		
		System.out.println("----------------");
		
	}
	
	
	@Test(priority = 4)
	public void getProductDataAPIWith_Extract_Body() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response= given()
			.queryParam("limit", 5)
		.when()
			.get("/products");
		
		
		JsonPath js= response.jsonPath();
		
		
		int firstProductID= js.getInt("[0].id");
		System.out.println("ID: "+firstProductID);
		
		String firstProductTitle= js.getString("[0].title");
		System.out.println("Title: "+firstProductTitle);
		
		
		float firstProductPrice= js.getFloat("[0].price");
		System.out.println("Price: "+firstProductPrice);
		
		
		int count= js.getInt("[0].rating.count");
		System.out.println("Count: "+count);
		
		System.out.println("-----------------");
		
	}
	
	
	@Test(priority = 5)
	public void getProductDataAPIWith_Extract_Body_withArray() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response= given()
			.queryParam("limit", 20)
		.when()
			.get("/products");
		
		
		JsonPath js= response.jsonPath(); //json array
		
		List<Integer> idList= js.getList("id");
		System.out.println("Size: "+idList.size());
		System.out.println("idList: "+idList);
		
		List<String> titleList= js.getList("title");
		System.out.println("titleList: "+titleList);
		
		List<Object> rateList= js.getList("rating.rate"); //Object store any data type (Float+Int)
		System.out.println("rateList: "+rateList);
		
		
		List<Integer> countList= js.getList("rating.count");
		System.out.println("countList: "+countList);
		
		
		
		for(int i=0;i<idList.size();i++) {
			int id= idList.get(i);
			String title= titleList.get(i);
			Object rate= rateList.get(i);
			int count= countList.get(i);
			
			System.out.println("ID: "+id+" Title: "+title+" Rate: "+rate+" Count: "+count);
		}
		
	
		System.out.println("------------------");
		
	}
	
	
	@Test(priority = 6)
	public void getUserAPIWith_Extract_Body_withJson() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response= given()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/7017684");
		
		response.then().log().all().statusCode(200)
		.body("size()", equalTo(5));
		
		JsonPath js= response.jsonPath();
		
		int id= js.get("id");
		String name= js.getString("name");
		String email= js.getString("email");
		
		System.out.println("ID: "+id);
		System.out.println("Name: "+name);
		System.out.println("Email: "+email);
		
		
		System.out.println("---------------------");
		
	}
	
	@Test(priority = 7)
	public void getUserAPIWith_Extract_Body_withJson_Extract() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given()
			.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d")
		.when()
			.get("/public/v2/users/7017684")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.response();
		
		
		int userID = response.path("id");
		String userEmail = response.path("email");
		
		System.out.println("userEmail: "+userEmail);
		System.out.println("userID: "+userID);
	
	}
	
	
	
}
