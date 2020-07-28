package GET_Request;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import Assertion.Assertions_types;
import Configurations.Configuration;
import Initials.Basic_Init;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class Request_Data {
	
	public RequestSpecification httpRequest;
    
	public static Logger logger =  Logger.getLogger(Request_Data.class);
	
	
	 
	@BeforeTest
	 public void setup () throws IOException	
     {
	     
		 Basic_Init.setBaseURI(Configuration.URL); 
		 httpRequest = RestAssured.given();
		 httpRequest.accept("application/json");
	
     }
	
	 
	 @Test
	    public void TC01_GetWeatherDetailsForValidCity() 
	 {

		  Connection connection = null;
		  Statement statement = null;
		  ResultSet resultset = null;
		  
		    try 
		      
	        {
	        	 Response response = httpRequest.get();
	 	         Assertions_types.checkStatusIs200(response);
	 	         Assertions_types.checkStatusLine(response);
	 	       
	 	        
	 	   	    String contentType = response.header("Content-Type");
	 		    System.out.println("Content-Type value: " + contentType);
	 	   	    
	 	   	    JsonPath jsonPathEvaluator = response.jsonPath();
	 	 	   
	        	Class.forName(Configuration.DBSourceServer);
	        	connection = DriverManager.getConnection(Configuration.DBconnectionUrl); 
	        	statement = connection.createStatement();
	        	
	            String sql_statement = "SELECT TOP 1000 [current_user_url]\r\n" + 
	            		"      ,[hub_url]\r\n" + 
	            		"  FROM [master].[dbo].[github]";
	            
	            resultset = statement.executeQuery(sql_statement);

	            // Iterate through the data in the result set and display it.
	            while (resultset.next()) {
	                System.out.println(resultset.getString("current_user_url") + " " + resultset.getString("hub_url"));
	                // Validate the response
	  	          Assert.assertEquals(jsonPathEvaluator.get("current_user_url"), resultset.getString("current_user_url"), "Passed");
	  	          Assert.assertEquals(jsonPathEvaluator.get("hub_url"), resultset.getString("hub_url"), "Passed");
	  		    
	            }
	            
	           	
	        }
	        // Handle any errors that may have occurred.
	        catch (Exception e) {
	           logger.warn(e);
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
			 logger.warn(ex);
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
			 logger.warn(ex);
			}
		 
		
	 }
	
	


}
