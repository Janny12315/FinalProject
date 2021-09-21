package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CabinetPage {

    public static final SelenideElement cabinet = $x("(//a[@href=\"/cabinet\"])[2]");
    public static final SelenideElement welcome = $x("//*[@class=\"main-title-page\"]");
    public static final SelenideElement profile = $x("(//a[@href=\"/profile.ru.html\"])[2]");


    public boolean checkCabinet(){
        cabinet.should(Condition.visible);
        welcome.should(Condition.visible);
        profile.should(Condition.visible);
       return cabinet.isDisplayed()&&welcome.isDisplayed()&&profile.isDisplayed();
    }
}
