package DataDrivenTesting_AddNewEmployees;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class addNewEmployees {

	@Test(dataProvider="empDataProvider")
	void postNewEmployees(String ename, String esal, String eage) {
		
		//Define Base URI
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		//Request Object
		
		RequestSpecification httprequest=RestAssured.given();
		
		//Prepare body
		
		JSONObject requestParams=new JSONObject();
		
		//prepare parameters that we need to send using Hashmap
		requestParams.put("name", ename);
		requestParams.put("salary", esal);
		requestParams.put("age", eage);
		
		//We need to specify content type for our request as application/json using header
		httprequest.header("Content-Type", "application/json");
		
		//we need to attach the request params/body to our request and specify that we are sending Json format
		httprequest.body(requestParams.toJSONString());
		
		//pass the method and define response object
		
		Response response=httprequest.request(Method.POST, "/create");
		
		String responseBody=response.getBody().asString();
		
		System.out.println("Response Body is:" +responseBody);
		
		//Validate status code
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		//Validate response body
		Assert.assertEquals(responseBody.contains(ename), true);	
		//Validate response body
		Assert.assertEquals(responseBody.contains(esal), true);
		
		Assert.assertEquals(responseBody.contains(eage), true);
		
		
		//Validate success code
		Assert.assertEquals(response.jsonPath().get("status"), "success");
		
	}
		
		@DataProvider(name="empDataProvider")
		String[][] getEmpData() throws IOException
		{
			System.out.println(System.getProperty("user.dir"));
			//make connection to excel
			//String path=System.getProperty("user.home") + "/src/test/java/DataDrivenTesting_AddNewEmployees/empdata.xlsx";
			String path=System.getProperty("Users/Anand/eclipse-workspace/RestAssuredApiTesting/src/test/java/DataDrivenTesting_AddNewEmployees/empdata.xlsx");
			//get the rowcount and columncount
			
			int rowcount=XLUtility.getRowCount(path, "Sheet1");
			int cellcount=XLUtility.getCellCount(path, "Sheet1", 1);
			
			
			String empdata[][]=new String[rowcount][cellcount];
			
			for(int i=1;i<=rowcount;i++){
				for(int j=0; j<cellcount;j++) {
					empdata[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j);
				}
			}
			
			return(empdata);
	
		}
		
		
		
	}

		
