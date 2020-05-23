import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_PrintHeaders {

	@Test
	public void getPrintHeaders() {
		// Define Base URI

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Define request object

		RequestSpecification httprequest = RestAssured.given();

		// Define Response object

		Response response = httprequest.request(Method.GET, "/comments");

		// get response body

		String responseBody = response.getBody().asString();

		System.out.println("Response body is:" + responseBody);

		// Get ALL headers
		Headers allHeaders = response.headers(); // capture ALL headers from response. Type HEADERS is a map (key,
													// value)

		for (Header header : allHeaders) {
			System.out.println(header.getName() + "   " + header.getValue());
		}
	}

}
