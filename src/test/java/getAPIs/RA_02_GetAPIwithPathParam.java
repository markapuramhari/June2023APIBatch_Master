package getAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RA_02_GetAPIwithPathParam {

	
	@Test(priority = 1)
	public void getCircuitsAPIWithPathParamTest() {
		
		RestAssured.baseURI="https://ergast.com";
		
		given()
			.log().all()
			.pathParam("year", "2010")
		.when()
			.get("/api/f1/{year}/circuits.json")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("MRData.CircuitTable.season", equalTo("2010"))
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(19))
			.body("MRData.CircuitTable.Circuits[0].circuitId", equalTo("albert_park"));
	}
	
	
	@DataProvider
	public Object[][] getCircuitsyearData() {
		
		return new Object[][] {
			{"2016",21},
			{"2017",20},
			{"1966",9},
			{"2023",22},
			{"2010",19}
		};
		
		
	}
	
	
	@Test(priority = 2,dataProvider = "getCircuitsyearData")
	public void getCircuitsAPIWith_year_DataProviderTest(String seasonyear,int totalCircuits) {
		
		RestAssured.baseURI="https://ergast.com";
		
		given()
			.pathParam("year", seasonyear)	
		.when()
			.get("/api/f1/{year}/circuits.json")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("MRData.CircuitTable.season", equalTo(seasonyear))
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));
	}
	
}
