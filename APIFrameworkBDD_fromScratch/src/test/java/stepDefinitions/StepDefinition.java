package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {



	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	TestDataBuild tb = new TestDataBuild();
	//Static usage - All test case in the current execution will refer to same variable value or else 
	//it will set to null after each test case execution and throw nullPointer exception
	static String Place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(tb.addPlacePayload(name,language,address));  
	}

	@When("User calls {string} with {string} request")
	public void user_calls_with_post_request(String resource , String method) {

		APIResources getResource = APIResources.valueOf(resource);
		System.out.println("The resource is--> " + getResource.getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();

		if(method.equalsIgnoreCase("GET")) {
			response=res.when().get(getResource.getResource());
		}

		else if(method.equalsIgnoreCase("POST")) {
			response=res.when().post(getResource.getResource());

		}

		else if(method.equalsIgnoreCase("DELETE")) {
			response=res.when().delete(getResource.getResource());

		}
		System.out.println("The Response is --> " + response.asString());
	} 
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);

	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		// Write code here that turns the phrase above into concrete actions


		assertEquals(getJSONPath(response, keyValue), expectedValue);
	}



	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		//requestSpec

		Place_id = getJSONPath(response, "place_id");

		res = given().spec(requestSpecification()).queryParam("place_id", Place_id);
		user_calls_with_post_request(resource,"GET");
		String actualName = getJSONPath(response, "name");
		assertEquals(actualName, expectedName);
	}



	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		res = given().spec(requestSpecification()).body(tb.deletePlacePayload(Place_id));
	}












}
