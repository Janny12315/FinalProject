package api.steps;

import api.connect.HttpConnect;
import api.connect.ParseJsonTour;
import api.connect.Tour;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Tag;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckResponseWithTourSteps {
    static List<Tour> tours = new ArrayList<>();

    @Tag("bdd")
    @When("get list with tours")
    public void getListWithTours() throws IOException {
        String response = HttpConnect.getToursWithParameters();
        tours = ParseJsonTour.parseTour(response);
    }

    @Tag("bdd")
    @Then("Check all tours have right price {int}")
    public void checkAllToursHaveRightPrice(int priceMax) {
        for (Tour tour : tours) {
            assertTrue(tour.getPriceMax() < priceMax);
        }
    }

    @Tag("bdd")
    @Then("^Check all tours have right dates$")
    public void checkAllToursHaveRightDates(List<String> localDates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Tour tour : tours) {
            assertTrue(tour.getDate().isAfter(LocalDate.parse(localDates.get(0), formatter)));
            assertTrue(tour.getDate().isBefore(LocalDate.parse(localDates.get(1), formatter)));
        }
    }

    @Tag("bdd")
    @Then("Check all tours have right cityOut")
    public void checkAllToursHaveRightCityOut(String cityFrom) {
        for (Tour tour : tours) {
            assertEquals(tour.getCityFrom(), cityFrom);
        }
    }
}
