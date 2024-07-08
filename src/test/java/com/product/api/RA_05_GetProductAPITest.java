package com.product.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RA_05_GetProductAPITest {
	
	@Test(priority = 1)
	public void getProductTest_With_POJO() {
		
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response= given()
		
		.when()
			.get("/products");
	
	
//json to POJO -->De-serialization
	
	
	ObjectMapper mapper=new ObjectMapper();
	
	//mapper.readValue(response.getBody().asString(), Product.class);
	
	try {
		ProductPOJO product[]=mapper.readValue(response.getBody().asString(), ProductPOJO[].class);
		
		for(ProductPOJO p: product) {
			System.out.println("ID: "+p.getId());
			System.out.println("Title: "+p.getTitle());
			System.out.println("Price: "+p.getPrice());
			System.out.println("Description "+p.getDescription());
			System.out.println("Category: "+p.getCategory());
			System.out.println("Image: "+p.getImage());
			System.out.println("Rate: "+p.getRating().getRate());
			System.out.println("Count "+p.getRating().getCount());
			System.out.println("-----------");
		}
		
		
		
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	

	}	
	
	
	
	
	@Test(priority = 2)
	public void getProductTest_With_POJO__with_Lombok() {
		
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response= given()
		
		.when()
			.get("/products");
	
	
//json to POJO -->De-serialization
	
	
	ObjectMapper mapper=new ObjectMapper();
	
	//mapper.readValue(response.getBody().asString(), Product.class);
	
	try {
		//Product product[]=mapper.readValue(response.getBody().asString(), Product[].class);
		ProductLombok product[]=mapper.readValue(response.getBody().asString(), ProductLombok[].class);
		
		for(ProductLombok p: product) {
			System.out.println("ID: "+p.getId());
			System.out.println("Title: "+p.getTitle());
			System.out.println("Price: "+p.getPrice());
			System.out.println("Description "+p.getDescription());
			System.out.println("Category: "+p.getCategory());
			System.out.println("Image: "+p.getImage());
			System.out.println("Rate: "+p.getRating().getRate());
			System.out.println("Count "+p.getRating().getCount());
			System.out.println("-----------");
		}
		
		
		
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	

	}
	
	
	
}
