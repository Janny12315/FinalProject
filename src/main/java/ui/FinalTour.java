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
    private static final SelenideElement payment = $x("//*[@class=\"t-btn-book-detail t-calculator price-button progress-button\"]");
    private static final SelenideElement photorama = $(".fotorama__img");

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

    public void clickPayment(){
        photorama.click();
        payment.should(Condition.visible).click();
    }

    public int priceWithoutTax(){
        return Integer.parseInt(price.should(Condition.visible).getText().split(" ")[0]);
    }

}


