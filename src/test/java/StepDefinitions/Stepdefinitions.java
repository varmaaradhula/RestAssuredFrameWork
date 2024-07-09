package StepDefinitions;

import PojoClasses.AddPlace;

//import static jdk.nio.zipfs.ZipFileAttributeView.AttrID.method;
import static org.junit.Assert.*;
import PojoClasses.Location;
import PojoClasses.AddplaceResPojo;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Stepdefinitions extends Utils {
    RequestSpecification request;
    Response response;
    String addplacerespString;
    TestDataBuild data = new TestDataBuild();
    static String place_Id;
    //PrintStream log;

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        request = given().log().all().spec(requestSpecification()).body(data.addPlacepayload(name,language,address));
    }
   /* @Given("Add place payload")
    public void add_place_payload() throws IOException {
        request = given().log().all().spec(requestSpecification()).body(data.addPlacepayload());
    }*/

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resources, String method) {

        APIResources resourceAPI = APIResources.valueOf(resources);
        if(method.equalsIgnoreCase("POST"))
        response = request.when().post(resourceAPI.getResouces()).then().spec(responseSpecification())
                .extract().response();
        else if(method.equalsIgnoreCase("GET"))
            response = request.when().get(resourceAPI.getResouces()).then().spec(responseSpecification())
                    .extract().response();

    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer expctedstatusCode) {
        Assert.assertEquals(response.getStatusCode(),expctedstatusCode);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String Keyvalue, String ExpectedValue) {
        Assert.assertEquals(getJsonPath(response,Keyvalue),ExpectedValue);
    }

    @Then("verify place_Id created in maps to {string} using {string} with {string} http request")
    public void verify_place_id_created_in_maps_to_using_with_http_request(String expctedName, String resource, String method) throws IOException {

        APIResources resourceAPI = APIResources.valueOf(resource);
        place_Id = getJsonPath(response,"place_id");
        request = given().log().all().spec(requestSpecification()).queryParam("place_id",place_Id);

        user_calls_with_post_http_request(resource, method);

        String actualName = getJsonPath(response,"name");

        assertEquals(expctedName,actualName);


    }

    @Given("DeleteAPI payload")
    public void delete_api_payload() throws IOException {

        request = given().log().all().spec(requestSpecification()).body(data.deleteApiPayload(place_Id));

    }

}
