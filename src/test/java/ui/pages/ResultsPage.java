package ui.pages;

import com.codeborne.selenide.*;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class ResultsPage {
    private static final SelenideElement cityOutResult = $x("//*[@class=\"font_medium\"]");
    private static final String countriesInResults = "//*[@class=\"city-name map-marker-ico show_map_link\"]";
    private static final ElementsCollection nightsInResults = $$x("//*[@class=\"icon-spacer\"]/div[@class=\"type\"]");
    private static final ElementsCollection datesInResults = $$x("//*[@class=\"inline-visible icon-spacer fav-tourinfo\"]/div[@class=\"type\"]");
    private static final ElementsCollection currencyInResults = $$x("//*[@class=\"hover-hint-container top-hint-pos\"]/a");
    private static final ElementsCollection pansionInResults = $$x("//*[@class=\"type fav-mealplan\"]");
    private static final ElementsCollection starsInResults = $$x("//*[@data-title=\"Информация о «звёздности» отеля является субъективной оценкой туроператора (в зависимости от места расположения отеля, качества обслуживания и т.д.)\"]");

    private static Logger logger = Logger.getLogger(Banner.class);

    public void checkResultAvailable() {
        Configuration.timeout = 50000;
        $$x(countriesInResults).get(0).shouldBe(Condition.visible);
        Configuration.timeout = 4000;
    }

    public void checkIntermediateResultAvailable() {
        while (currencyInResults.size() < 3) {
            $x("//*[@href=\"javascript:void(0);\"]").shouldBe(Condition.visible).click();
        }
    }

    public boolean checkCountries(String country) {
        logger.debug("Check country: " + country + " : " + $$x(countriesInResults).stream().map(se -> se.getText()).collect(Collectors.toList()));
        return $$x(countriesInResults).stream().map(se -> se.getText()).allMatch(s -> s.contains(country));
    }

    public boolean checkNight(int night) {
        logger.debug("Check night: " + night + " : " + nightsInResults.stream().map(se -> Integer.parseInt(se.getText().split(" ")[1])).collect(Collectors.toList()));
        return nightsInResults.stream().map(se -> Integer.parseInt(se.getText().split(" ")[1])).allMatch(n -> n >= night && n < (night + 4));
    }

    public boolean checkDate(int dayBegin) {
        LocalDate selectedDay = LocalDate.of(2021, 10, dayBegin);
        LocalDate selectedDayAndWeek = selectedDay.plusDays(8);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        logger.debug("Check dayBegin: " + dayBegin + " : " + datesInResults.stream().map(se -> LocalDate.parse(se.getText().substring(0, 10), formatter)).collect(Collectors.toList()));
        return datesInResults.stream().map(se -> LocalDate.parse(se.getText().substring(0, 10), formatter)).allMatch(s -> s.isAfter(selectedDay.minusDays(1)) && s.isBefore(selectedDayAndWeek));
    }

    public boolean checkCityOut(String cityOut) {
        logger.debug("Check cityOut: " + cityOut + " : " + cityOutResult.getText().split(" ")[0]);
        return cityOutResult.getText().split(" ")[0].equals(cityOut);
    }

    public boolean checkCurrency(String currency) {
        logger.debug("Check currency: " + currency + " : " + currencyInResults.stream().filter(SelenideElement::isDisplayed).map(SelenideElement::getText).collect(Collectors.toList()));
        return currencyInResults.stream().filter(SelenideElement::isDisplayed).map(SelenideElement::getText).allMatch(s -> s.contains(Currency.getCurrency(currency).getIcon()));
    }

    public boolean checkStars(int star) {
        logger.debug("Check star: " + star + " : " + starsInResults.stream().filter(SelenideElement::isDisplayed).map(s -> s.getAttribute("class")).collect(Collectors.toList()));
        return starsInResults.stream().filter(SelenideElement::isDisplayed).map(s -> s.getAttribute("class")).filter(Objects::nonNull).allMatch(s -> s.contains(String.valueOf(star)));
    }

    public boolean checkPansion(String pansion) {
        logger.debug("Check pansion: " + pansion + " : " + pansionInResults.stream().filter(SelenideElement::isDisplayed).map(s -> s.getAttribute("data-mealplan")).collect(Collectors.toList()));
        return pansionInResults.stream().filter(SelenideElement::isDisplayed).map(s -> s.getAttribute("data-mealplan")).filter(Objects::nonNull).allMatch(s -> s.contains(pansion));
    }

    public List<String> selectTour(int number) {
        ArrayList<String> list = new ArrayList<>();
        list.add($x(String.format("(//div[@class=\"h5\"]/a)[%d]", number)).getText());
        list.add($x(String.format("(//div[@class=\"hover-hint-container top-hint-pos\"]/a)[%d]", number)).getText());
        list.add($x(String.format("(//*[@class=\"type\"])[%d]", number)).getText().substring(0, 10));
        $x(String.format("(//*[@class=\"t-btn-todetail price-button detail-link fav-detailurl\"])[%d]", number)).click();
        return list;
    }

}


