import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * 
 * @author Anand
 *
 */
public class TC001_GetRequest {

	@Test
	void getWeatherData() {
//Define base URI

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

//represents the request object
		RequestSpecification httprequest = RestAssured.given();

//response object
		Response response = httprequest.request(Method.GET, "/employees");

//print in console. Extract the body and convert to string and store in responseBody

		String responseBody = response.getBody().asString();

		System.out.println("Response body is:" + responseBody);

//status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" + statusCode);
		Assert.assertEquals(statusCode, 200);

//status line verification
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

}
