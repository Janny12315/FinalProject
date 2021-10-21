package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Banner {
    public SelenideElement banner1;
    public SelenideElement banner2;
    public SelenideElement banner3;
    public SelenideElement banner4;
    public SelenideElement banner5;

    public Banner() {
        banner1 = $x("(//*[@class=\"banner-text\"]/b)[1]");
        banner2 = $x("(//*[@class=\"banner-text\"]/b)[2]");
        banner3 = $x("(//*[@class=\"banner-text\"]/b)[3]");
        banner4 = $x("(//*[@class=\"banner-text\"]/b)[4]");
        banner5 = $x("(//*[@class=\"banner-text\"]/b)[5]");
    }

    public String switchBanner(int numberBanner) {
        switch (numberBanner) {
            case 1:
                $x("//*[@href=\"10529\"]").click();
                return banner1.should(Condition.visible).getText();
            case 2:
                $x("//*[@href=\"12789\"]").click();
                return banner2.should(Condition.visible).getText();
            case 3:
                $x("//*[@href=\"12752\"]").click();
                return banner3.should(Condition.visible).getText();
            case 4:
                $x("//*[@href=\"13412\"]").click();
                return banner4.should(Condition.visible).getText();
            case 5:
                $x("//*[@href=\"13317\"]").click();
                return banner5.should(Condition.visible).getText();
            default:
                return "text not found";
        }
    }
}
