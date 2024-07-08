package multibody;

import org.testng.annotations.Test;

//import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class RA_07_BodyAPITest {
	
	
	@Test(priority = 1)
	public void bodyWithTextTest() {
		
		
		RestAssured.baseURI="http://httpbin.org";
		String payload="This is Hari";
		
		Response response= given()
			.log().all()
			.contentType(ContentType.TEXT)
			.body(payload)
		.when()
			.post("/post");
				
		response.prettyPrint();	
			
		 JsonPath jp= response.jsonPath(); 
		 String data= jp.getString("data");

		//String data= JsonPath.read(response, ".data");
		
		System.out.println("DATA: "+data);
		
	}
	
	
	@Test(priority = 2)
	public void bodyWithJavaScriptTest() {
		
		
		RestAssured.baseURI="http://httpbin.org";
		String payload="function login(){\r\n"
				+ "    let x = 10;\r\n"
				+ "    let y= 20;\r\n"
				+ "    console.log(x+y);\r\n"
				+ "}";
		
		Response response= given()
			.log().all()
			.header("Content-Type","application/javascript")
			.body(payload)
		.when()
			.post("/post");
				
		response.prettyPrint();	
			
		 JsonPath jp= response.jsonPath(); 
		 String data= jp.getString("data");

		//String data= JsonPath.read(response, ".data");
		
		System.out.println("DATA: "+data);
		
	}
	
	
	@Test(priority = 3)
	public void bodyWithHTMLTest() {
		
		
		RestAssured.baseURI="http://httpbin.org";
		String payload="<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "    <h1>\r\n"
				+ "        \"Hi\"\r\n"
				+ "    </h1>\r\n"
				+ "</html>";
		
		Response response= given()
			.log().all()
			.contentType(ContentType.HTML)
			.body(payload)
		.when()
			.post("/post");
				
		response.prettyPrint();	
			
		 JsonPath jp= response.jsonPath(); 
		 String data= jp.getString("data");

		//String data= JsonPath.read(response, ".data");
		
		System.out.println("DATA: "+data);
		
	}
	
	
	@Test(priority = 4)
	public void bodyWithXMLTest() {
		
		
		RestAssured.baseURI="http://httpbin.org";
		String payload="<note>\r\n"
				+ "\r\n"
				+ "<to>tt</to>\r\n"
				+ "<form>rff</form>\r\n"
				+ "<body>Hari</body>\r\n"
				+ "\r\n"
				+ "</noted>";
		
		Response response= given()
			.log().all()
			.contentType(ContentType.XML)
			.body(payload)
		.when()
			.post("/post");
				
		response.prettyPrint();	
			
		 JsonPath jp= response.jsonPath(); 
		 String data= jp.getString("data");

		//String data= JsonPath.read(response, ".data");
		
		System.out.println("DATA: "+data);
		
	}
	
	
	@Test(priority = 5)
	public void bodyWithFormDataMultiPartTest() {
		
		
		RestAssured.baseURI="http://httpbin.org";
		
		
		Response response= given()
			.log().all()
			.contentType(ContentType.MULTIPART)
			.multiPart("name", "hari")
			.multiPart("fileName",new File("C:/Users/harib/Desktop/invoice.pdf"))
		.when()
			.post("/post");
				
		response.prettyPrint();	
			
		 JsonPath jp= response.jsonPath(); 
		 String data= jp.getString("files");

		//String data= JsonPath.read(response, ".data");
		
		System.out.println("DATA: "+data);
		
	}
	
	
	@Test(priority = 6)
	public void bodyWithBinaryTest() {
		
		
		RestAssured.baseURI="http://httpbin.org";
		
		
		Response response= given()
			.log().all()
			.contentType(ContentType.ANY)
			.header("Content-type","application/pdf")
			.body(new File("C:/Users/harib/Desktop/invoice.pdf"))
		.when()
			.post("/post");
				
		response.prettyPrint();	
			
		 JsonPath jp= response.jsonPath(); 
		 String data= jp.getString("data");

		//String data= JsonPath.read(response, ".data");
		
		System.out.println("DATA: "+data);
		
	}
	
	@Test(priority = 7)
	public void bodyWithurlencodedTest() {
		
		//get access token-POST
				
				RestAssured.baseURI="https://test.api.amadeus.com";
				
				String accessToken= given()
					.header("Content-type","application/x-www-form-urlencoded")
					.contentType(ContentType.URLENC)
					.formParam("grant_type", "client_credentials")
					.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
					.formParam("client_secret", "VjjgfcJilNAzcSJw")
				.when()
					.post("/v1/security/oauth2/token")
				.then()
					.log().all()
					.assertThat()
					.statusCode(200)
					.extract()
					.path("access_token");
				
				System.out.println("accessToken "+accessToken);
				
				
			}
	

}
