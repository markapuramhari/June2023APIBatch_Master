package _08_com.pet.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import _08_com.pet.api.PetLombok.Category;
import _08_com.pet.api.PetLombok.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class RA_06_CreatePetTest {
	
	@Test(priority = 1)
	public void createPetUser() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category=new Category(1,"Dog");
		List<String> photoUrls= Arrays.asList("https://www.dog.com","https://www.dog1.com");
		
		Tag tag1=new Tag(5,"red");
		Tag tag2=new Tag(5,"red");
		List<Tag> tags= Arrays.asList(tag1,tag2);
		
		PetLombok pet=new PetLombok(300,category,"Rakkey",photoUrls,tags,"available");
		
		
		Response response= given()
			.log().all()
			.contentType(ContentType.JSON)
			.body(pet) //Serialization
		.when()
			.post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
			
//De-serialization
		
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			PetLombok petRes= mapper.readValue(response.getBody().asString(), PetLombok.class);
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			System.out.println(petRes.getCategory().getName());
			System.out.println(petRes.getCategory().getClass());
			
			System.out.println(petRes.getPhotoUrls());
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());
			
			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());
			
			
			
			Assert.assertEquals(pet.getId(),petRes.getId());
			
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Test(priority = 2)
	public void createPet_WithBuilderPattern_User() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category=	new Category.CategoryBuilder()
					.id(2)
					.name("Animal")
					.build();
		
		Tag tag1= new Tag.TagBuilder()
					.id(3)
					.name("red")
					.build();
		
		Tag tag2= new Tag.TagBuilder()
				.id(1)
				.name("black")
				.build();
		
		PetLombok pet=new PetLombok.PetLombokBuilder()
					.id(200)
					.category(category)
					.name("Rokkey")
					.photoUrls(Arrays.asList("https://www.cat.com","https://www.cat1.com"))
					.tags(Arrays.asList(tag1,tag2))
					.status("aviable")
					.build();
					
					
					
		
		
		Response response= given()
			.log().all()
			.contentType(ContentType.JSON)
			.body(pet) //Serialization
		.when()
			.post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
			
//De-serialization
		
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			PetLombok petRes= mapper.readValue(response.getBody().asString(), PetLombok.class);
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			System.out.println(petRes.getCategory().getName());
			System.out.println(petRes.getCategory().getClass());
			
			System.out.println(petRes.getPhotoUrls());
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());
			
			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());
			
			
			
			Assert.assertEquals(pet.getId(),petRes.getId());
			
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	

}
