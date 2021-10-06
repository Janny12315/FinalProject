package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TourSelector {

    private static final SelenideElement selectButtonCountryIn = $("#countries");
    private static final SelenideElement selectButtonCityOut = $("#cities");
    private static final SelenideElement selectButtonNight = $("#nightsMin");
    private static final SelenideElement selectButtonDate = $("#dateRange-from");
    private static final SelenideElement searchButton = $x("//*[@type=\"button\"]");
    private static final SelenideElement backMonth = $x("//a[@class=\"ui-datepicker-prev ui-corner-all\"]");
    private static final SelenideElement firstmonth = $x("(//*[@class=\"ui-datepicker-group ui-datepicker-group-first\"]//span)[2]");
    private static final SelenideElement lastmonth = $x("(//*[@class=\"ui-datepicker-group ui-datepicker-group-last\"]//span)[2]");

    private static Logger logger = Logger.getLogger(Banner.class);

    public TourSelector selectCityOut(String city) {
        Select select = new Select(selectButtonCityOut.should(Condition.visible));
        while (!select.getFirstSelectedOption().getText().equals(city)) {
            select.selectByVisibleText(city);
            //logger.debug("Select city: " + city + " : " + select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        }
        return this;
    }

    public TourSelector selectCountryIn(String country) {
        Select select = new Select(selectButtonCountryIn.should(Condition.visible));
        while (!select.getFirstSelectedOption().getText().equals(country)) {
            select.selectByVisibleText(country);
            //logger.debug("Select country: " + country + " : " + select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        }
        return this;
    }

    public TourSelector selectDateBegin(int day, String month) {
        selectButtonDate.should(Condition.visible).click();
        while (!firstmonth.isDisplayed()) {
            selectButtonDate.click();
        }
        String path = null;
        if (firstmonth.getText().equals(month)) {
            path = String.format("//*[@class=\"ui-datepicker-group ui-datepicker-group-first\"]//a[text()=%d]", day);
        }
        if (lastmonth.getText().equals(month)) {
            path = String.format("//*[@class=\"ui-datepicker-group ui-datepicker-group-last\"]//a[text()=%d]", day);
        }
        $x(path).click();
        return this;
    }

    public TourSelector selectNight(int night) {
        Select select = new Select(selectButtonNight);
        select.selectByVisibleText(String.valueOf(night));
        while (Integer.parseInt(select.getFirstSelectedOption().getText()) != night)
            select.selectByVisibleText(String.valueOf(night));
        return this;
    }

    public void clickSearchButton() {
        searchButton.should(Condition.enabled).click();
    }
}
