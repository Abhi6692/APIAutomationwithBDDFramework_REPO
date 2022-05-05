package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceBody_AddPlaceAPI;
import pojo.Location_AddPlaceAPI;

public class TestDataBuild {

	public AddPlaceBody_AddPlaceAPI addPlacePayload(String name, String language, String address) {


		AddPlaceBody_AddPlaceAPI ap = new AddPlaceBody_AddPlaceAPI ();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);

		Location_AddPlaceAPI loc = new Location_AddPlaceAPI();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		return ap;

	}

	public String deletePlacePayload(String placeId) {

		return "{\r\n \"place_id\":\""+placeId+"\"\r\n}";
	}


}
