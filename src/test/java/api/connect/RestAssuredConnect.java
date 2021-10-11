package api.connect;

import static io.restassured.RestAssured.given;

public class RestAssuredConnect {

    public static void checkCode(int code) {
        String url = "https://search.tez-tour.com/tariffsearch/byCountry?countryId=1104&cityId=345&locale=ru&xml=true";
        given().get(url).then().statusCode(code);
    }

}
