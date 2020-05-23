import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidateResponseBody {

	@Test
	public void getValidateResponseBody() {

		// Define base URI
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Define Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Define Response object and pass the method
		Response response = httprequest.request(Method.GET, "/comments");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		// validate JSON body content
		Assert.assertEquals(responseBody.contains("Emma@joanny.ca"), true);

	}
}
