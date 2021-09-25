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
    private static final SelenideElement selectStars = $x("(//*[@class=\"menu_title \"])[2]");
    private static final SelenideElement selectPriceforCurrency = $x("//*[@class=\"menu_title\"]");

    public void changeCurrency(String currency) {
        Selenide.sleep(2000);
        if (selectPriceforCurrency.isDisplayed()) {
            selectPriceforCurrency.click();
        }
        selectCurrency.click();
        $x(String.format("(//a[@%s])[3]", Currency.getCurrency(currency).getLink())).click();
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

    public IntermediateSelection() {
        Configuration.timeout = 20000;
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

enum Currency {
    USD("rel=\"5561\"", "$"),
    BYN("rel=\"533067\"", "BYN"),
    RUB("rel=\"8390\"", "₽"),
    EUR("rel=\"18864\"", "€");
    private final String link;
    private final String icon;

    Currency(String link, String icon) {
        this.link = link;
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public String getIcon() {
        return icon;
    }

    public static Currency getCurrency(String currency) {
        switch (currency) {
            case "BYN":
                return Currency.BYN;
            case "USD":
                return Currency.USD;
            case "EUR":
                return Currency.EUR;
            case "RUB":
                return Currency.RUB;
            default:
                return null;
        }
    }
}