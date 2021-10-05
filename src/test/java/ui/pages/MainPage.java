package ui.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public static final SelenideElement closePopUpWindow = $x("//*[@id=\"fancybox-close\"]");
    public static final SelenideElement personalArea = $x("//*[@target=\"_blank\"]");
    public static final SelenideElement countriesFrame = $x("// a [text()=\"Страны\"]");
    public static final SelenideElement imgLogo = $x("//*[@alt=\"logo\"]");

    public static final SelenideElement changeLocation = $("#city-dropdown");
    public static final SelenideElement setMinsk = $x("(//*[@href=\"https://www.teztour.by/ru/minsk/\"])[2]");
    public static final SelenideElement getLocation = $x("//*[@class=\"dd-city\"]");
    public static final ElementsCollection countries = $$x("//*[@class=\"country-list-flag-item \"]");

    @Step("Закрыть окошко с геолокацией")
    public void closePopUpWindow() {
        closePopUpWindow.should(Condition.visible).click();
    }
    public boolean isButtonAvailable() {
        return closePopUpWindow.exists();
    }
    public boolean isLogoAvailable() {
        return imgLogo.isDisplayed();
    }

    @Step("Установить локацию Минск")
    public void setLocationMinsk() {
        Configuration.timeout = 20000;
        changeLocation.should(Condition.visible).click();
        Configuration.timeout = 4000;
        setMinsk.should(Condition.visible).click();
    }

    public MainPage clickCountries() {
        countriesFrame.click();
        return this;
    }

    public int countCountries() {
        countries.get(1).should(Condition.visible);
        return countries.size();
    }

    public String getLocation() {
        return getLocation.should(Condition.visible).getText();
    }

    public void clickPersonalArea() {
        personalArea.click();
    }

}
