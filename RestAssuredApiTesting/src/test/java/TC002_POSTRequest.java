import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POSTRequest {

	@Test
	void postData() {

		// define base URI

		RestAssured.baseURI = "https://reqres.in/api/users";

		// define request object
		RequestSpecification httprequest = RestAssured.given();

		// Sending payload along with request
		JSONObject requestParams = new JSONObject();

		requestParams.put("FirstName", "Saniya");
		requestParams.put("LastName", "Anand");

		// define content type using headers method. we need to specify we are sending
		// JSON body
		httprequest.header("Content-Type", "application/json");

		// we added request parameters and converted to JSON. Ready to call POST after
		// this
		httprequest.body(requestParams.toJSONString());

		// define response object
		Response response = httprequest.request(Method.POST, "?page=1");

		// print in console. Extract the body and convert to string and store in
		// responseBody

		String responseBody = response.getBody().asString();

		System.out.println("Response body is:" + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" + statusCode);
		Assert.assertEquals(statusCode, 201);

//we can also verify success code. wheerever success path term is there in the json code. get the value of that

		String successCode = response.jsonPath().get("Success code");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");

	}

}
