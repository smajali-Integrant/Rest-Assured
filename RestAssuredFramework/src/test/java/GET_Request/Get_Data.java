package GET_Request;

import org.testng.annotations.BeforeTest;
import io.restassured.*;
import org.testng.annotations.Test;

import Assertion.Assertions_types;
import Initials.*;
import Configurations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_Data {
	
	public RequestSpecification httpRequest;
	
	@BeforeTest
	 public void setup (){
	        
		 Basic_Init.setBaseURI(Configuration.URL); 
		 httpRequest = RestAssured.given();
	
	    }
	
	 
	 @Test
	    public void TC01_GetWeatherDetailsForValidCity()  {
	     
		 try
		 {
	        Response response = httpRequest.get("/Amman");
	        Assertions_types.checkStatusIs200(response);
	        Assertions_types.checkStatusLine(response);
	        Assertions_types.checkResponseBody(response, "Amman");
	   	    System.out.println("Response Body is =>  " + response.asString());
	   	    
	   	    String contentType = response.header("Content-Type");
		    System.out.println("Content-Type value: " + contentType);
		 }
		 catch(Exception ex)
			{
				ex.printStackTrace();
			}
	    }
	
	
	@Test
	 public void TC02_GetWeatherDetailsForInvalidCity()
	 {   
		try
		{
		 Response response = httpRequest.get("/233434534");
		 Assertions_types.checkStatusIs200(response);
	     Assertions_types.checkStatusLine(response);
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	 }
	
	@Test
	 public void TC03_GetWeatherDetailsForEmptyCity()
	 {   
		 try
		 {
			 Response response = httpRequest.get("/");
			 Assertions_types.checkStatusIs200(response);
		     Assertions_types.checkStatusLine(response);
		 }
		 
		 catch(Exception ex)
			{
				ex.printStackTrace();
			}
		 
		
	 }
}


 
