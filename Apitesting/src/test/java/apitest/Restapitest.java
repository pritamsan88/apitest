package apitest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Restapitest {
	String id;
	String token;
	String phonenumber;
	String emailaddress;
	@BeforeTest
	public void startup()
	{
		RestAssured.baseURI="https://dev.mantramedic.com";
	}
	
	@Test(priority = 1 )
	public void customerlogin()
	{
		
		
		Pojofile2 pro=new Pojofile2("9748049729", "6415", "en");
		Response response=RestAssured.
				given()
				.header("Content-Type","application/json")
				.body(pro)
				.when()
				.post("/api/customer/Login")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		
		System.out.println("Respone body :"+response.toString());
		id=response.jsonPath().getString("data.id");
		System.out.println(" extracted id here : "+id);
		token=response.jsonPath().getString("token");
		System.out.println(" extracted id here : "+token);
		phonenumber=response.jsonPath().getString("data.phone_number");
		System.out.println("Extracted phonenumber :-"+ phonenumber);
		emailaddress=response.jsonPath().getString("data.email_address");
		System.out.println("Extracted email address:- "+ emailaddress);
		
		
	}
	
	
	

}
