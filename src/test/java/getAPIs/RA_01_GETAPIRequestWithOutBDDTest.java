package getAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RA_01_GETAPIRequestWithOutBDDTest {
	
	RequestSpecification request;
	
//Rest Assured NON BDD Approach
	
	@BeforeTest
	public void setup() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		request= RestAssured.given();	
		request.header("Authorization","Bearer 384ded16bee6c5ee59a2e644ca69cf78e10ca68943a525f20bf009944bacd83d");
		
	}
	
	
	
	@Test(priority = 1)
	public void getAllUsersAPITest() {
		
//Request:

		Response response= request.get("/public/v2/users/");
		
//Response:
		
		//status code
		int statusCode= response.statusCode();
		System.out.println("Status Code: "+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		
//status message
		String statusMsg= response.statusLine();
		System.out.println("Status Message: "+statusMsg);
		Assert.assertEquals(statusMsg, "HTTP/1.1 200 OK");
		
		
//featch the body
		response.prettyPrint();
		
		
//featch header:
		String contentType= response.header("Content-Type");
		System.out.println(contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		
		System.out.println("------------");
//featch all headers
		List<Header> headersList = response.headers().asList();
		System.out.println("Headers list: "+headersList.size());
		
		for(Header h: headersList) {
			System.out.println(h.getName()+":"+h.getValue());
		}
	}
	
	
	@Test(priority = 2)
	public void getAllUsersWithQueryParameterAPITest() {
		
//Request:
		
		request.queryParam("name", "naveen");
		request.queryParam("status", "active");
		
		Response response= request.get("/public/v2/users");
		
//Response:
		
		//status code
		int statusCode= response.statusCode();
		System.out.println("Status Code: "+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		
//status message
		String statusMsg= response.statusLine();
		System.out.println("Status Message: "+statusMsg);
		Assert.assertEquals(statusMsg, "HTTP/1.1 200 OK");
		
		
//featch the body
		response.prettyPrint();
		
		
	}
	
	
	
	@Test(priority = 3)
	public void getAllUsersWithQueryParameter_WithHashMap_APITest() {
		
//Request:
			
//		request.queryParam("name", "naveen");
//		request.queryParam("status", "active");
		
		Map<String,String> queryParamsMap=new HashMap<String,String>(); 
		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("status", "active");
		
		request.queryParams(queryParamsMap);
		
		Response response= request.get("/public/v2/users");
		
//Response:
		
		//status code
		int statusCode= response.statusCode();
		System.out.println("Status Code: "+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		
//status message
		String statusMsg= response.statusLine();
		System.out.println("Status Message: "+statusMsg);
		Assert.assertEquals(statusMsg, "HTTP/1.1 200 OK");
		
		
//featch the body
		response.prettyPrint();
		
		
	}
	
	

}
