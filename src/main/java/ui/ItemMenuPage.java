package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class ItemMenuPage {

    public ItemMenuPage() {
    }

    public SelenideElement itemMenu(String itemMenuName) {
        return $x(String.format("//*[text()=\"%s\"]", itemMenuName));
    }

    public SelenideElement getH1(String name) {
        return $x(String.format("//h1[text()=\"%s\"]", name));
    }

    public boolean IsCorrectItemMenuPage(String name) {
        return getH1(name).should(Condition.visible).isDisplayed();
    }


}
