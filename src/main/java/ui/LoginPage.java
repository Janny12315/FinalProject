package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    public static final SelenideElement loginInput = $x("//*[@autocomplete=\"new-password\"]");
    public static final SelenideElement passInput = $x("(//*[@type=\"password\"])[3]");
    public static final SelenideElement continueButton = $x("(//*[@class=\"yellow-btn-50 t-step-btn\"])[2]");
    public static final SelenideElement enterButton = $x("//*[@name=\"submit\"]");
    public static final SelenideElement errorAuthorization = $x("(//*[@class=\"error error-message\"])[3]");

    public LoginPage inputLogin(String login) {
        loginInput.should(Condition.visible).setValue(login);
        return this;
    }

    public LoginPage inputPass(String pass) {
        passInput.should(Condition.visible).setValue(pass);
        return this;
    }

    public LoginPage clickContinue() {
        continueButton.click();
        return this;
    }

    public LoginPage enter() {
        enterButton.click();
        return this;
    }

    public boolean isErrorAuthorization() {
        errorAuthorization.should(Condition.visible);
        return errorAuthorization.isDisplayed();
    }
}
