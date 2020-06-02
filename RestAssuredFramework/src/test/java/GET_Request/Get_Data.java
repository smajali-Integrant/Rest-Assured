package GET_Request;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


import Assertion.Assertions_types;
import Configurations.Configuration;
import Initials.Basic_Init;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.*;



public class Get_Data {
	
	public RequestSpecification httpRequest;
    
	public static Logger logger =  Logger.getLogger(Get_Data.class);
	
	
	
	 
	@BeforeTest
	 public void setup () throws IOException{
	     
		
		 Basic_Init.setBaseURI(Configuration.URL); 
		 httpRequest = RestAssured.given();
				

				PropertyConfigurator.configure("src\\test\\java\\log4j.properties");
				
			
	
	    }
	
	 
	 @Test
	    public void TC01_GetWeatherDetailsForValidCity()  {
	     
		 try
		 {
	        Response response = httpRequest.get("/Amman");
	        Assertions_types.checkStatusIs200(response);
	        Assertions_types.checkStatusLine(response);
	        Assertions_types.checkResponseBody(response, "Amman");
	        
	      /*  logger.info("Log4j appender configuration is successful !!");
			 logger.debug("This is Debug");
		     logger.info("This is Info");
		     logger.warn("This is Warn");
		     logger.error("This is Error");
		     logger.fatal("This is Fatal");

		    logger.debug("this is a debug log message");
		    logger.info("this is a information log message");
		    logger.warn("this is a warning log message");*/
	   	   // System.out.println("Response Body is =>  " + response.asString());
	   	    
	   	    String contentType = response.header("Content-Type");
		  //  System.out.println("Content-Type value: " + contentType);
		    
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
		 Response response = httpRequest.get("/s/233434534");
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
