package Assertion;

import org.testng.Assert;

import io.restassured.response.Response;

public class Assertions_types {

	   public static void checkStatusIs200 (Response res) {
	        
	        Assert.assertEquals(res.getStatusCode(), 200, "Correct status code returned");
	    }
	
	   public static void checkStatusLine (Response res) {
	        
		   Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK", "Correct status Line returned");
	    }
	   
	   public static void checkResponseBody (Response res, String city) {
		   
		   String bodyAsString = res.getBody().asString();
		   Assert.assertEquals(bodyAsString.contains(city), true, "Response body contains" + city);
	   }
		
}
