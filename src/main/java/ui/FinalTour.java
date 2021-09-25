package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class FinalTour {
    private static final SelenideElement hotel = $x("//h1");
    private static final SelenideElement price = $("#minimumPrice");
    private static final SelenideElement dayDepart = $x("//*[@class=\"selector-box\"]/input");

    public List<String> getInFoFinalTour() {
        List<String> infoFinalTour = new ArrayList<>();
        Configuration.timeout = 40000;
        infoFinalTour.add(hotel.getText());
        infoFinalTour.add(price.should(Condition.visible).getText());
        Configuration.timeout = 4000;
        dayDepart.click();
        infoFinalTour.add(dayDepart.getAttribute("data-date"));
        return infoFinalTour;
    }
}


