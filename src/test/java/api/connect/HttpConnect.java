package api.connect;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpConnect {
    static HttpClient client = HttpClients.createDefault();

    public static String getToursWithParameters() throws IOException {
        StringRequest stringRequestMyClass=new StringRequest();
        stringRequestMyClass.withPriceMin(0)
                .withPriceMax(700)
                .withNightsMin(6)
                .withNightsMax(6)
                .withHotelClassId(269506)
                .withAccommodationId(2)
                .withRAndBId(2424)
                .withCityId(345)
                .withCountryId(1104)
                .withDayAfter("20.10.2021")
                .withSDayBefore("30.10.2021");
        HttpGet firstRequest = new HttpGet(stringRequestMyClass.getStringRequest());
        HttpResponse response = client.execute(firstRequest);
        String responseStr=EntityUtils.toString(response.getEntity());
        System.out.println(response.getStatusLine().getStatusCode());
        return responseStr;
    }

    public static HttpResponse getGeneralReference() throws IOException {
        HttpGet firstRequest = new HttpGet("https://search.tez-tour.com/tariffsearch/references?locale=ru&formatResult=true&xml=false");
        HttpResponse response = client.execute(firstRequest);
        return response;
    }

    public static HttpResponse getCountryReference() throws IOException {
        HttpGet firstRequest = new HttpGet("https://search.tez-tour.com/tariffsearch/byCountry?countryId=1104&cityId=345&locale=ru&xml=true");
        HttpResponse response = client.execute(firstRequest);
        return response;
    }






    }



//        String stringRequest = "https://search.tez-tour.com/tariffsearch/getResult?" +
//                "priceMin=0&" +
//                "priceMax=700&" +
//                "nightsMin=6&" +
//                "nightsMax=6&" +
//                "hotelClassId=269506&" +
//                "accommodationId=2&" +
//                "rAndBId=2424&" +
//                "cityId=345&" +
//                "countryId=1104&" +
//                "after=20.10.2021&" +
//                "before=30.10.2021&";
//                "hotelClassBetter=true&" +
//                "rAndBBetter=true&" +
//                "hotelInStop=false";