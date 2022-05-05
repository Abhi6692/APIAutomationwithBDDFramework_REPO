package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	//This step will ensure that the below method is executed before "@DeletePlace" scenario
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Preconditions 
		//Write a code which will give you place id
		//Execute only if the place id is null

		StepDefinition sd = new StepDefinition();
		if(StepDefinition.Place_id==null) {

			sd.add_place_payload_with("Shetty", "French", "Barrackpore");
			sd.user_calls_with_post_request("addPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}

	}}
