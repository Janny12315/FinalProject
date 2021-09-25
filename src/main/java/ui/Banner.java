package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
//import org.apache.log4j.Logger;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Banner {
    public SelenideElement banner1;
    public SelenideElement banner2;
    public SelenideElement banner3;
    public SelenideElement banner4;

    public Banner(List list) {
        banner1 = $x(String.format("//*[text()=\"%s\"]", list.get(0)));
        banner2 = $x(String.format("//*[text()=\"%s\"]", list.get(1)));
        banner3 = $x(String.format("//*[text()=\"%s\"]", list.get(2)));
        banner4 = $x(String.format("//*[text()=\"%s\"]", list.get(3)));
    }

//    private static Logger logger=Logger.getLogger(Banner.class);
    public String switchBanner(int numberBanner) {
//        logger.debug("banner number "+numberBanner+" has been clicked");
        switch (numberBanner) {
            case 1:
                $x("//*[@href=\"12752\"]").click();
                return banner1.should(Condition.visible).getText();
            case 2:
                $x("//*[@href=\"10529\"]").click();
                return banner2.should(Condition.visible).getText();
            case 3:
                $x("//*[@href=\"10773\"]").click();
                return banner3.should(Condition.visible).getText();
            case 4:
                $x("//*[@href=\"12789\"]").click();
                return banner4.should(Condition.visible).getText();
            default:
                return "text not found";

        }
    }
}
