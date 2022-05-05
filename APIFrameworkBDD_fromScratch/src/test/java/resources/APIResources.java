package resources;
//Special class in JAVA which is collection of method and constants 
public enum APIResources {


	addPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	

	APIResources(String resource) {
		
		this.resource = resource;
	}
	
	public String getResource() {
		
		return resource;
	}
}

