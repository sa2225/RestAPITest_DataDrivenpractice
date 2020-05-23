import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_ValidateHeaders {

	@Test
	void getHeaders() {
		// Define Base URI

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Define Request Object

		RequestSpecification httprequest = RestAssured.given();

		// Define response object

		Response response = httprequest.request(Method.GET, "/comments");

		// Get the body in String format

		String responseBody = response.getBody().asString();

		System.out.println("Response body is:" + responseBody);

		// Validate header Content-Type

		String contentType = response.header("Content-Type");
		System.out.println("Content Type is:" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

		// Validate header Transfer-Encoding
		String transferEncoding = response.header("Transfer-Encoding");
		System.out.println("Transfer encoding is:" + transferEncoding);
		Assert.assertEquals(transferEncoding, "chunked");

	}

}
