package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class IntermediateSelection {
    private static final SelenideElement selectCurrency = $x("//*[@class=\"styled_select\"]/a");
    private static final SelenideElement selectTypeOfFood = $x("//div[contains(text(),\"Питание\")]");
    private static final SelenideElement selectStars = $x("(//*[@class=\"menu_title \"])[2]");
    private static final SelenideElement selectPriceForCurrency = $x("//div[contains(text(),\"Цена\")]");

    public IntermediateSelection() {
        Configuration.timeout = 40000;
    }

    public void changeCurrency(String currency) {
        Selenide.sleep(2000);
        while (!selectCurrency.isDisplayed()) {
            selectPriceForCurrency.shouldBe(Condition.visible).click();
        }
        selectCurrency.click();
        String path = String.format("(//a[@%s])[3]", Currency.getCurrency(currency).getLink());
        $x(path).click();
    }

    public void changeTypeOfFood(String typeOfFood) {
        if (selectTypeOfFood.isDisplayed()) {
            selectTypeOfFood.click();
        }
        switch (typeOfFood) {
            case "Любое":
                $("#pansions-rate-9006284-").should(Condition.visible).click();
                break;
            case "Размещение без питания":
                $("#pansions-rate-9006284").should(Condition.visible).click();
                break;
            case "Только завтраки":
                $("#pansions-rate-9006285").should(Condition.visible).click();
                break;
            case "Завтрак и ужин":
                $("#pansions-rate-9006286").should(Condition.visible).click();
                break;
            case "Завтрак, обед и ужин":
                $("#pansions-rate-9006287").should(Condition.visible).click();
                break;
            case "Все включено":
                $("#pansions-rate-9006288").should(Condition.visible).click();
                break;
            case "Ультра все включено":
                $("#pansions-rate-9006289").should(Condition.visible).click();
                break;
            default:
                System.out.println("There are no such type of food." +
                        "change to one of them: Любое, Размещение без питания, Только завтраки, Завтрак и ужин, Завтрак, обед и ужин, Все включено, Ультра все включено");
                break;
        }
    }

    public void changeStar(int star) {
        if (selectStars.isDisplayed()) {
            selectStars.click();
        }
        switch (star) {
            case 2:
                $x("//*[@value=\"-9006277-2\"]").should(Condition.visible).click();
                break;
            case 3:
                $x("//*[@value=\"-9006278-3\"]").should(Condition.visible).click();
                break;
            case 4:
                $x("//*[@value=\"-9006279-4\"]").should(Condition.visible).click();
                break;
            case 5:
                $x("//*[@value=\"-9006280-5\"]").should(Condition.visible).click();
                break;
            default:
                System.out.println("There are no such stars level");
                break;
        }
    }
}










