Feature: Validating Place API's

#Test 1
@AddPlace
Scenario Outline: Verify if Place is being added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" with "Post" request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify place_id created maps to "<name>" using "getPlaceAPI"

Examples: 
	| name  | language | address  | 	
	|AAHouse| English  | Address A|
#	|ABHouse| Hindi    | Address B|
#	|ACHouse| French   | Address C|

#Test 2

@DeletePlace
Scenario: Verify if delete place functionality is working

Given DeletePlace Payload
When User calls "deletePlaceAPI" with "Post" request
Then the API call is success with status code 200
And  "status" in response body is "OK"