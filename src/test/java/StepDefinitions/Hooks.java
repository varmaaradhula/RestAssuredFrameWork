package StepDefinitions;

//import org.junit.Before;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        Stepdefinitions step = new Stepdefinitions();

        if (Stepdefinitions.place_Id == null) {
            step.add_place_payload_with("Aradhula", "HINDI", "Yerukalapudi,Tenali");
            step.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            step.verify_place_id_created_in_maps_to_using_with_http_request("Aradhula", "getPlaceAPI", "GET");
        }
    }
}
