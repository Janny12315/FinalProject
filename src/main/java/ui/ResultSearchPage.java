package ui;

import com.codeborne.selenide.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ResultSearchPage {
    public static final SelenideElement cityOutResult = $x("//*[@class=\"checked-place\"]");
    public static final ElementsCollection resultsCountries = $$x("//*[@class=\"city-name map-marker-ico show_map_link\"]");
    public static final ElementsCollection resultsNights = $$x("//*[@class=\"icon-spacer\"]/div[@class=\"type\"]");
    public static final ElementsCollection resultsDates = $$x("//*[@class=\"inline-visible icon-spacer fav-tourinfo\"]/div[@class=\"type\"]");
//    public static final SelenideElement buttonSearch = $(".icon_set_1_icon-42");
//    public static final SelenideElement clarifyDate = $x("//*[@data-type=\"period\"]");
//    public static final SelenideElement openMobileForm = $("#open_form_mobile");
//    public static final SelenideElement clarifyNight = $(".nights-text");
    public static final SelenideElement currency = $x("//div[@class=\"styled_select\"]/a[@class=\"selectBox styled selectBox-dropdown\"]");

//    public ResultSearchPage clarifyDateBegin(int day) {
//        Configuration.timeout = 20000;
//        if (openMobileForm.isDisplayed()) {
//            openMobileForm.click();
//            clarifyDate.click();
//        } else {
//            clarifyDate.should(Condition.visible).click();
//        }
//        Configuration.timeout = 4000;
//        String path = String.format("//span[text()=\"%d\"]/..", day);
//        $x(path).should(Condition.visible).click();
//        $x(path).should(Condition.visible).click();
//        return this;
//    }

//    public ResultSearchPage clarifyNight(int night) {
//        clarifyNight.should(Condition.visible).click();
//        $x(String.format("//*[@onclick=\"SearchForm.Tour.Nights.V3.set(this, %d)\"]/span", night)).should(Condition.visible).click();
//        $x(String.format("//*[@onclick=\"SearchForm.Tour.Nights.V3.set(this, %d)\"]/span", night)).should(Condition.visible).click();
//        return this;
//    }

    public boolean checkResultCountries(String country) {
        Configuration.timeout = 50000;
        resultsCountries.get(1).should(Condition.visible);
        Configuration.timeout = 4000;
        long resultsWithRightCountries = resultsCountries.stream().filter(se -> se.getText().contains(country)).count();
        resultsCountries.stream().forEach(System.out::println);

        return resultsWithRightCountries == resultsCountries.size();
    }

    public boolean checkNightInResults(int night) {
        long resultsWithRightNights = resultsNights.stream().filter(se -> se.getText().contains(String.valueOf(night))).count();
        resultsNights.stream().forEach(System.out::println);
        return resultsWithRightNights == resultsNights.size();
    }

    public boolean checkDateInResults(int dayBegin) {
        long resultsWithRightDayBegin = resultsDates.stream().filter(se -> se.getText().contains(String.valueOf(dayBegin))).count();
        resultsDates.stream().forEach(System.out::println);
        return resultsWithRightDayBegin == resultsDates.size();
    }

    public boolean checkCityOutInResult(String cityOut) {
        return cityOutResult.getText().equals(cityOut);
    }
//
//    public void clickSearchButton() {
//        buttonSearch.should(Condition.enabled).click();
//    }

    public List<String> selectTour(int number) {
        Configuration.timeout = 50000;
        resultsCountries.get(1).should(Condition.visible);
        Configuration.timeout = 4000;
        ArrayList<String> list = new ArrayList<>();
        list.add($x(String.format("(//div[@class=\"h5\"]/a)[%d]", number)).getText());
        list.add($x(String.format("(//div[@class=\"hover-hint-container top-hint-pos\"]/a)[%d]", number)).getText());
        list.add($x(String.format("(//*[@class=\"type\"])[%d]", number)).getText().substring(0, 10));
        $x(String.format("(//*[@class=\"t-btn-todetail price-button detail-link fav-detailurl\"])[%d]", number)).click();
        Selenide.sleep(100);
        return list;
    }

}


