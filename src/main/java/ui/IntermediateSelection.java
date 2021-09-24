package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class IntermediateSelection {
    private static final SelenideElement selectCurrency = $x("//*[@class=\"styled_select\"]/a");
    private static final SelenideElement selectTypeOfFood = $x("//div[contains(text(),\"Питание\")]");
    private static final SelenideElement selectPriceforCurrency = $x("//*[@class=\"menu_title\"]");

    public void changeCurrency(CurrencyTour currencyTour) {
        Selenide.sleep(2000);
        if(selectPriceforCurrency.isDisplayed()){
            selectPriceforCurrency.click();
        }
        selectCurrency.should(Condition.visible).click();
        $x(String.format("(//a[@%s])[3]", currencyTour.getAttr())).click();
    }
    public void changeTypeOfFood(String typeOfFood) {
        selectTypeOfFood.should(Condition.visible).click();
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

    public IntermediateSelection() {
        Configuration.timeout=10000;
    }

    public void changeStar(int star) {
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
    enum CurrencyTour {
        USD("rel=\"5561\""),
        BYN("rel=\"533067\""),
        RUB("rel=\"8390\""),
        EUR("rel=\"18864\"");
        private String attr;

        CurrencyTour(String attr) {
            this.attr = attr;
        }

        public String getAttr() {
            return attr;
        }
    }