package api.Steps;

import api.HttpConnect;
import io.cucumber.java.en.*;
import org.apache.http.HttpResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckGeneralReferenceSteps {

    static HttpResponse response ;
    @When("get catalog of parameters")
    public void get_catalog_of_parameters() throws IOException {
        response= HttpConnect.getGeneralReference();
    }

    @Then("Check status code {int}")
    public void checkStatusCode(int code) {
        assertEquals(code, response.getStatusLine().getStatusCode());
    }

    @When("get country guide")
    public void getCountryGuide() throws IOException {
        response=HttpConnect.getCountryReference();
    }
}

