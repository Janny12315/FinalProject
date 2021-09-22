package ui;

import com.codeborne.selenide.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class ResultSearchPage {
    private static final SelenideElement cityOutResult = $x("//*[@class=\"checked-place\"]");
    private static final ElementsCollection resultsCountries = $$x("//*[@class=\"city-name map-marker-ico show_map_link\"]");
    private static final ElementsCollection resultsNights = $$x("//*[@class=\"icon-spacer\"]/div[@class=\"type\"]");
    private static final ElementsCollection resultsDates = $$x("//*[@class=\"inline-visible icon-spacer fav-tourinfo\"]/div[@class=\"type\"]");


    public ResultSearchPage checkResultAvailable() {
        Configuration.timeout = 70000;
        resultsCountries.get(1).should(Condition.visible);
        Configuration.timeout = 4000;
        return this;
    }

    public boolean checkCountries (String country) {
        long resultsWithRightCountries = resultsCountries.stream().filter(se -> se.getText().contains(country)).count();
        return resultsWithRightCountries == resultsCountries.size();
    }

    public boolean checkNight(int night) {
        boolean isNightRight = false;
        List<String> list = resultsNights.stream().map(se -> se.getText().split(" ")[1]).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i)) >= night)
                isNightRight = true;
            else {
                isNightRight = false;
                break;
            }
        }
        return isNightRight;
    }

    public boolean checkDate(int dayBegin) {

        LocalDate selectedDay = LocalDate.of(2021, 9, dayBegin-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        List<LocalDate> list = resultsDates.stream().map(se -> LocalDate.parse(se.getText().substring(0, 10), formatter)).collect(Collectors.toList());
        boolean isDateRight = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isAfter(selectedDay))
                isDateRight = true;
            else {
                isDateRight = false;
                System.out.println(" даты не сх "+list.get(i)+" "+selectedDay);
                break;
            }
        }
        return isDateRight;
    }

    public boolean checkCityOut(String cityOut) {
        System.out.println("заявленный город: "+cityOut+ " сравниваем "+cityOutResult.getText()+"!");
        return cityOutResult.should(Condition.visible).getText().equals(cityOut);
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


