package _05_jayWayJsonPathValidatorTest;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RA_05_JaywayJsonPathTest {
	
	
	@Test(priority = 1)
	 public void getCircuitData() {
		 
		 RestAssured.baseURI="http://ergast.com";
		  
		Response response= given() 
		 .when()
		 	.get("/api/f1/2017/circuits.json");
		
		response.then().log().all();
		 
		String jsonResponse= response.asString();
		System.out.println(jsonResponse);
		
		int totalCircuits= JsonPath.read(jsonResponse,"$.MRData.CircuitTable.Circuits.length()");
		System.out.println("totalCircuits: "+totalCircuits);
	
		List<String> countryList= JsonPath.read(jsonResponse, "$..Circuits..country");//$.MRData.CircuitTable.Circuits.[country]
		System.out.println("countryListSize: "+countryList.size());
		System.out.println("countryList: "+countryList);
		
		
	 }
	
	
	@Test(priority = 2)
	public void getProductTest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given()
			
		.when()
			.get("/products");
		
		String jsonResponse= response.asString();
		System.out.println(jsonResponse);
		
		
		List<Float> rateLessThen3= JsonPath.read(jsonResponse,"$[?(@.rating.rate <3)].rating.rate");
		
		System.out.println("rateLessThen3: "+rateLessThen3);
		System.out.println("rateLessThen3size: "+rateLessThen3.size());
		
		
		System.out.println("--------------");
//2 attributs		
		List<Map<String, Object>> jeweleryList=JsonPath.read(jsonResponse,"$[?(@.category=='jewelery')].['title','price']");
		System.out.println("jeweleryList: "+jeweleryList);
		
		
		for(Map<String, Object> product: jeweleryList) {
			String title= (String)product.get("title");
			Object price= (Object)product.get("price");
			
			System.out.println("title: "+title);
			System.out.println("price: "+price);
		}
		
//three attributs		
		System.out.println("--------------");
		
		List<Map<String, Object>> jeweleryList3=JsonPath.read(jsonResponse,"$[?(@.category=='jewelery')].['title','price','id']");
		System.out.println("jeweleryList: "+jeweleryList);
		
		
		for(Map<String, Object> product: jeweleryList3) {
			String title= (String)product.get("title");
			Object price= (Object)product.get("price");
			Integer id= (Integer)product.get("id");
			
			System.out.println("title: "+title);
			System.out.println("price: "+price);
			System.out.println("ID: "+id);
			
		}
		
	
	}
	

}
