package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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
        if(!new MainPage().isLogoAvailable()){
            new MainPage().closePopUpWindow();
        }
    }


    @Order(1)
    @ParameterizedTest
    @CsvSource("src/test/resources/BannersNames")
    @Tag("smoke")
    public void testBanner(String path) throws IOException {

        List<String> list = Files.readAllLines(Paths.get(path));

        Banner banner = new Banner(list);

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).toUpperCase(), banner.switchBanner(i + 1));
        }
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource("jane-yaponia@mail.ru, 11111111")
    public void testAuthorization(String login, String password) {
        new MainPage().clickPersonalArea();

        switchTo().window(1);

        new LoginPage().inputLogin(login).clickContinue().inputPass(password).enter();

        Assertions.assertTrue(new CabinetPage().checkCabinet());

        Selenide.closeWindow();

        switchTo().window(0);

    }

    @Order(3)
    @ParameterizedTest
    @CsvSource("jane-yaponia@mail.ru, 11118111")
    public void testAuthorizationWithIncorrectDate(String login, String password) {

        new MainPage().clickPersonalArea();

        switchTo().window(1);

        new LoginPage().inputLogin(login).clickContinue().inputPass(password).enter();

        Assertions.assertTrue(new LoginPage().isErrorAuthorization());

        Selenide.closeWindow();

        switchTo().window(0);
    }

    @Order(4)
    @ParameterizedTest
    @CsvSource("src/test/resources/ItemsMenu")
    @Tag("smoke")
    public void testItemMenu(String path) throws IOException {

        List<String> list = Files.readAllLines(Paths.get(path));

        MainPage mainPage = new MainPage();

        for (int i = 0; i < list.size(); i++) {
            String itemName = list.get(i);
            mainPage.itemMenu(itemName).click();
            switchTo().window(1);
            Assertions.assertTrue(new ItemMenuPage(itemName).IsCorrectItemMenuPage(itemName));
            Selenide.closeWindow();
            switchTo().window(0);
        }
    }

    @Order(5)
    @ParameterizedTest
    @CsvSource("31")
    public void testCountries(int count) {
        int countCountries = new MainPage().clickCountries().countCountries();
        assertEquals(count, countCountries);
    }


    @Order(6)
    @ParameterizedTest
    @CsvSource({"Киев, ОАЭ,30, 8",
            "Москва, Греция, 29, 28"})
    @Tag("integration")
    public void testTourSelection(String cityOut, String countryIn, int dayBegin, int night) {

        TourSelector tourSelector = new TourSelector();

        //LocalDate localDate=tourSelector.getSelectDate();
        LocalDate localDate=LocalDate.of(2021,9, dayBegin);

        tourSelector.selectCityOut(cityOut).selectCountryIn(countryIn).selectDateBegin(dayBegin).selectNight(night).clickSearchButton();

        switchTo().window(1);

        ResultSearchPage resultSearchPage=new ResultSearchPage();
        resultSearchPage.checkResultAvailable();
        //new ResultSearchPage().clarifyDateBegin(dayBegin).clarifyNight(night).clickSearchButton();
        try {
            assertAll(
                    () -> assertTrue(resultSearchPage.checkСountryInResults(countryIn)),
                    () -> assertTrue(resultSearchPage.checkNightInResults(night)),
                    () -> assertTrue(resultSearchPage.checkDateInResults(localDate)),
                    () -> assertTrue(resultSearchPage.checkCityOutInResult(cityOut))
            );
        }catch (Exception e){
            System.out.println("исключение");
            Selenide.closeWindow();
            switchTo().window(0);

        }


        Selenide.closeWindow();

        switchTo().window(0);
    }

    @Order(7)
    @ParameterizedTest
    @CsvSource("Минск, Египет, 11, 12, 1")
    @Tag("integration")
    public void testTourBooking(String cityOut, String countryIn, int night, int dayBegin, int selectResult) {

        new MainPage().setLocationMinsk();

        TourSelector tourSelector = new TourSelector();

        tourSelector.selectCityOut(cityOut).selectCountryIn(countryIn).selectDateBegin(dayBegin).selectNight(night).clickSearchButton();

        switchTo().window(1);

        ResultSearchPage resultSearchPage = new ResultSearchPage();

        List<String> infoTour = resultSearchPage.selectTour(selectResult);

        switchTo().window(2);

        List<String> resultTour = new FinalTour().getInFoFinalTour();

        for (int i = 0; i < infoTour.size(); i++) {
            assertEquals(infoTour.get(i), resultTour.get(i));
        }

        Selenide.closeWindow();

        switchTo().window(0);
    }

}
