package Initials;


import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Basic_Init {

  
	  
	public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }
	
	

	
}
