Feature: Validating Place API

@AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
     Given Add place payload with "<name>" "<language>" "<address>"
     When user calls "AddPlaceAPI" with "POST" http request
     Then the API call got success with status code 200
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And verify place_Id created in maps to "<name>" using "getPlaceAPI" with "GET" http request
  Examples:
     | name    | language | address            |
     | AAhouse | English  | World cross centre |

@DeletePlace
 Scenario: To test the delate place functionality
   Given DeleteAPI payload
   When user calls "deletePlaceAPI" with "POST" http request
   Then the API call got success with status code 200
   And "status" in response body is "OK"




