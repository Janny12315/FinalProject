package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private static final SelenideElement closePopUpOK = $("#modal-close");
    private static final SelenideElement booking = $x("//*[text()=\"Бронирование\")]");
    private static final SelenideElement timeBeforeCloseBooking = $("#countdownBox");
    private static final SelenideElement priceWith = $(".lg-currency");

    public void closePopUpOK() {
        Selenide.sleep(5000);
        webdriver().driver().getWebDriver().navigate().refresh();
        closePopUpOK.should(Condition.visible).click();
    }

    public boolean rightViewAndPrice(int priceWithoutTax,int tax) {
        booking.should(Condition.visible);
        timeBeforeCloseBooking.should(Condition.exist);
        int priceWithTax= Integer.parseInt(priceWith.getText().split(" ")[0]);
        return priceWithTax==priceWithoutTax+tax;
    }
}
