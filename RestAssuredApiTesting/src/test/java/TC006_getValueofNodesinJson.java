import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_getValueofNodesinJson {

	@Test
	public void getValueofNodes() {
		// Define baseURI

		RestAssured.baseURI = "https://reqres.in/api/";

		// Define Request Object

		RequestSpecification httprequest = RestAssured.given();

		// Define response object

		Response response = httprequest.request(Method.GET, "/users");

		// String responseBody=response.getBody().asString();
		// System.out.println("Response body is:" +responseBody);

		// we need to get json path in order to extract the node value so we use a
		// pre-defined class for that

		JsonPath jsonpath = response.jsonPath();

		JsonPath data = jsonpath.get("data");

		// System.out.println(jsonpath.get("data"));

	}

}
