package ui.main;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.pages.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UITest {

    @BeforeAll
    public static void setting() {

        Configuration.startMaximized = true;
        open("https://www.tez-tour.com/");
        while (!new MainPage().isButtonAvailable()) {
            open("https://www.tez-tour.com/");
        }
        new MainPage().closePopUpWindow();
    }

    @BeforeEach
    public void openSite() {
        open("https://www.tez-tour.com/");
        if (!new MainPage().isLogoAvailable()) {
            new MainPage().closePopUpWindow();
        }
    }

    @AfterEach
    public void closeWindow() {
        ArrayList<String> tabs = new ArrayList<String>(Selenide.webdriver().driver().getWebDriver().getWindowHandles());
        if (tabs.size() > 1) {
            Selenide.closeWindow();
            switchTo().window(0);
        }
    }

    @DisplayName("Проверка отображения баннеров")
    @Order(1)
    @ParameterizedTest
    @CsvSource("src/test/resources/datesForTests/BannersNames")
    @Tag("sanity")
    public void testBanner(String path) throws IOException {

        List<String> list = Files.readAllLines(Paths.get(path));
        Banner banner = new Banner();

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).toUpperCase(), banner.switchBanner(i + 1));
        }
    }

    @DisplayName("Авторизация. Вход в личный кабинет")
    @Order(2)
    @ParameterizedTest
    @CsvSource("jane-yaponia@mail.ru, 11111111")
    @Tag("authorization")
    public void testAuthorization(String login, String password) {
        new MainPage().clickPersonalArea();

        switchTo().window(1);

        new LoginPage().inputLogin(login).clickContinue().inputPass(password).enter();

        Assertions.assertTrue(new CabinetPage().checkCabinet());

    }

    @DisplayName("Авторизация некорректными данными. Сообщение об ошибке")
    @Order(3)
    @ParameterizedTest
    @CsvSource("jane-yaponia@mail.ru, 11118111")
    @Tag("authorization")
    public void testAuthorizationWithIncorrectDate(String login, String password) {

        new MainPage().clickPersonalArea();

        switchTo().window(1);

        new LoginPage().inputLogin(login).clickContinue().inputPass(password).enter();

        Assertions.assertTrue(new LoginPage().isErrorAuthorization());

    }

    @DisplayName("Проверка отображения информационного меню")
    @Order(4)
    @ParameterizedTest
    @CsvSource("src/test/resources/datesForTests/ItemsMenu")
    @Tag("sanity")
    public void testItemMenu(String path) throws IOException {

        List<String> list = Files.readAllLines(Paths.get(path));

        ItemMenuPage itemMenuPage = new ItemMenuPage();

        for (int i = 0; i < list.size(); i++) {
            String itemName = list.get(i);
            itemMenuPage.itemMenu(itemName).click();
            switchTo().window(1);
            Assertions.assertTrue(itemMenuPage.IsCorrectItemMenuPage(itemName));
            Selenide.closeWindow();
            switchTo().window(0);
        }
    }

    @DisplayName("Отображение списка стран")
    @Order(5)
    @ParameterizedTest
    @CsvSource({"31"})
    @Tag("sanity")
    public void testCountries(int count) {
        int countCountries = new MainPage().clickCountries().countCountries();
        assertEquals(count, countCountries);
    }

    @DisplayName("Подбор тура")
    @Order(6)
    @ParameterizedTest
    @CsvSource({"Киев, ОАЭ, 20, Октябрь, 10",
            "Москва, Греция, 20, Октябрь, 15"})
    @Tag("integration")
    public void testTourSelection(String cityOut, String countryIn, int dayBegin, String month, int night) {

        TourSelector tourSelector = new TourSelector();

        tourSelector.selectCityOut(cityOut).selectCountryIn(countryIn).selectNight(night).selectDateBegin(dayBegin, month).clickSearchButton();

        switchTo().window(1);

        ResultsPage resultsPage = new ResultsPage();

        resultsPage.checkResultAvailable();
        assertTrue(resultsPage.checkCountries(countryIn));
        assertTrue(resultsPage.checkNight(night));
        assertTrue(resultsPage.checkDate(dayBegin));
        assertTrue(resultsPage.checkCityOut(cityOut));

    }

    @DisplayName("Уточнение параметров тура")
    @Order(7)
    @ParameterizedTest
    @CsvSource({"EUR, 5, Только завтраки",
            "BYN, 3, Все включено"})
    @Tag("integration")
    public void testIntermediateSelection(String currency, int stars, String pansion) {
        open("https://tourist.teztour.by/toursearch/8d51bf63c719684b7e11c4fa6cac2c84/tourType/1/cityId/786/" +
                "before/27.10.2021/after/20.10.2021/countryId/7067673/minNights/11/maxNights/14/adults/2/flexdate/0/flexnight/0/" +
                "hotelTypeId/357603/mealTypeId/2424/rAndBBetter/yes/isTableView/0/lview/cls/noTicketsTo/no/noTicketsFrom/no/hotelInStop/no/" +
                "recommendedFlag/no/onlineConfirmFlag/no/tourMaxPrice/1500000/categoryGreatThan/yes/currencyId/533067/dtype/period/baggage/2.ru.html");
        ResultsPage resultsPage = new ResultsPage();
        resultsPage.checkResultAvailable();
        IntermediateSelection intermediateSelection = new IntermediateSelection();

        intermediateSelection.changeCurrency(currency);
        intermediateSelection.changeStar(stars);
        intermediateSelection.changeTypeOfFood(pansion);
        Selenide.sleep(1000);

        resultsPage.checkIntermediateResultAvailable();
        assertTrue(resultsPage.checkCurrency(currency));
        assertTrue(resultsPage.checkStars(stars));
        assertTrue(resultsPage.checkPansion(pansion));

    }


    @DisplayName("Подбор и выбор одного тура")
    @Order(8)
    @ParameterizedTest
    @CsvSource("Минск, Кипр, 25, Октябрь, 8")
    @Tag("End-to-End")
    public void testTourBooking(String cityOut, String countryIn, int dayBegin, String month, int night) {

        new MainPage().setLocationMinsk();

        TourSelector tourSelector = new TourSelector();

        tourSelector.selectCityOut(cityOut).selectCountryIn(countryIn).selectNight(night).selectDateBegin(dayBegin, month).clickSearchButton();

        switchTo().window(1);

        ResultsPage resultsPage = new ResultsPage();

        resultsPage.checkResultAvailable();

        List<String> infoTour = resultsPage.selectTour(1);

        switchTo().window(2);

        FinalTour finalTour = new FinalTour();

        List<String> resultTour = finalTour.getInFoFinalTour();

        for (int i = 0; i < infoTour.size(); i++) {
            assertEquals(infoTour.get(i), resultTour.get(i));
        }
    }
}
