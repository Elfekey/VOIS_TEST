package Postman_test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class pm_test {
	
	@Test
	public void test_assert() {
	 //assertion using response object 
		Response response = get("https://schema.getpostman.com/json/collection/v2.0.0/collection.json");
			String body = response.getBody().toString();
		int status_code =response.getStatusCode();
			System.out.println("The body is :"+response.getBody());
			System.out.println("The Status Code is :"+status_code);

	//assert on body
		Assert.assertEquals(status_code,200 );
		Assert.assertEquals(body,response.getBody().toString());
	System.out.println("------------------- \n THIS The End of First Test \n-------------------");
		//------------------- \n THIS The End of First Test \n-------------------
	}
	 
	@Test
	public void test_assert2() {
     //assertion using given and then 
		baseURI=("https://schema.getpostman.com/json");
		given().
			get("/collection/v2.0.0/collection.json").
		then().
			statusCode(200).
			body("definitions.auth.title", equalTo("Auth")).
			body("properties.item.description", equalTo("Items are the basic unit for a Postman collection. You can think of them as corresponding to a single API endpoint. Each Item has one request and may have multiple API responses associated with it."));
		
		System.out.println("------------------- \n THIS The End of Second Test \n-------------------");

	}



}
