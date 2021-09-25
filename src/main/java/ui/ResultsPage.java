package ui;

import com.codeborne.selenide.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class ResultsPage {
    private static final SelenideElement cityOutResult = $x("//*[@class=\"checked-place\"]");
    private static final String countriesInResults = "//*[@class=\"city-name map-marker-ico show_map_link\"]";
    private static final ElementsCollection nightsInResults = $$x("//*[@class=\"icon-spacer\"]/div[@class=\"type\"]");
    private static final ElementsCollection datesInResults = $$x("//*[@class=\"inline-visible icon-spacer fav-tourinfo\"]/div[@class=\"type\"]");
    private static final ElementsCollection currencyInResults = $$x("//*[@class=\"hover-hint-container top-hint-pos\"]/a");


    public void checkResultAvailable() {
        Configuration.timeout = 70000;
        $$x(countriesInResults).get(0).shouldBe(Condition.visible);
        Configuration.timeout = 4000;
    }

    public boolean checkCountries (String country) {
        return $$x(countriesInResults).stream().map(se -> se.getText()).allMatch(s->s.contains(country));
    }

    public boolean checkNight(int night) {
        return nightsInResults.stream().map(se -> Integer.parseInt(se.getText().split(" ")[1])).allMatch(n -> n >= night);
    }

    public boolean checkDate(int dayBegin) {

        LocalDate selectedDay = LocalDate.of(2021, 9, dayBegin-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        List<LocalDate> list = datesInResults.stream().map(se -> LocalDate.parse(se.getText().substring(0, 10), formatter)).collect(Collectors.toList());
        boolean isDateRight = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isAfter(selectedDay))
                isDateRight = true;
            else {
                isDateRight = false;
                break;
            }
        }
        return isDateRight;
    }

    public boolean checkCityOut(String cityOut) {
        System.out.println("заявленный город: "+cityOut+ " сравниваем "+cityOutResult.getText()+"!");
        return cityOutResult.getText().equals(cityOut);
    }


    public boolean checkCurrency (Currency currency) {
        return currencyInResults.stream().map(SelenideElement::getText).allMatch(s->s.contains(currency.getIcon()));
    }

    public boolean checkStars (int star) {
        ElementsCollection starsInResults=$$x(String.format("//*[@class=\"hotel-star-box hotel_star star_%d tipsy-hint is-inithint\"]", star));
        return $$x(countriesInResults).size()==starsInResults.size();
    }

    public boolean checkPansion (String pansion) {
        ElementsCollection starsInResults=$$x(String.format("//*[contains(text(), \"RO - %s\")]", pansion));
        return $$x(countriesInResults).size()==starsInResults.size();
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


