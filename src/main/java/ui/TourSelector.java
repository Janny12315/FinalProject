package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class TourSelector {

    public static final SelenideElement selectButtonCountryIn = $("#countries");
    public static final SelenideElement selectButtonCityOut = $("#cities");
    public static final SelenideElement selectButtonNight = $("#nightsMin");
    public static final SelenideElement selectButtonDate = $("#dateRange-from");
    public static final SelenideElement searchButton = $x("//*[@type=\"button\"]");
    public static final SelenideElement selectedDay = $x("//*[@class=\"  ui-datepicker-current-day\"]/a");
    public static final SelenideElement dayTo = $("#dateRange-to");

    public TourSelector selectCityOut(String city) {
//        selectButtonCityOut.should(Condition.visible).click();
//        String path = String.format("//option[text()=\"%s\"]", city);
//        $x(path).should(Condition.visible).click();
//        return this;
        Select select = new Select(selectButtonCityOut);
        select.selectByVisibleText(city);
        while (!select.getFirstSelectedOption().getText().equals(city))
            select.selectByVisibleText(city);
        return this;
    }

    public TourSelector selectCountryIn(String country) {
        Select select = new Select(selectButtonCountryIn);
        select.selectByVisibleText(country);
        while (!select.getFirstSelectedOption().getText().equals(country))
            select.selectByVisibleText(country);
        return this;
    }

    public TourSelector selectDateBegin(int day) {
        selectButtonDate.should(Condition.visible).click();
        String path = String.format("//a[text()=\"%d\"]", day);
        if ($x(path).isDisplayed())
            $x(path).click();
        else {
            selectButtonDate.click();
            $x(path).click();
        }
//        selectButtonDate.click();
//        if (!selectedDay.getText().equals(String.valueOf(day))) {
//            $x(path).click();
//        }
        return this;
    }


    public TourSelector selectNight(int night) {
        selectButtonNight.should(Condition.visible).click();
        String path = String.format("//option[@value=\"%d\"]", night);
        $x(path).should(Condition.visible).click();
        return this;
    }

    public void clickSearchButton() {
        searchButton.should(Condition.enabled).click();
    }

    public LocalDate getSelectDate() {
        selectButtonDate.should(Condition.visible).click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd.MM.uuuu" );
        LocalDate localDateTo=LocalDate.parse(dayTo.getAttribute("value"), formatter);
        System.out.println(localDateTo);
        return localDateTo.minusDays(7);
    }
}
