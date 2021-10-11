package api.steps;

import api.connect.HttpConnect;
import api.connect.RestAssuredConnect;
import io.cucumber.java.en.*;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckGeneralReferenceSteps {

    HttpResponse response ;
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

    @Then("Check response contains id Almaty {long}")
    public void checkResponseContainsCountry(long idAlmaty) throws IOException, ParseException {
        response= HttpConnect.getGeneralReference();
        String responseStr= EntityUtils.toString(response.getEntity());
        JSONObject obj = (JSONObject) new JSONParser().parse(responseStr);
        JSONArray cities = (JSONArray) obj.get("cities");
        JSONObject object= (JSONObject) cities.get(1);
        long id= (long) object.get("cityId");
        assertEquals(id, idAlmaty);
    }

    @Then("Check status code with RestAssured {int}")
    public void checkStatusCodeWithRestAssuredInt(int code) {
        RestAssuredConnect.checkCode(code);
    }
}

