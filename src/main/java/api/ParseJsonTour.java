package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseJsonTour {
    public static List<Tour> parseTour(String respString){

        JSONObject obj = null;
        try {
            obj = (JSONObject) new JSONParser().parse(respString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert obj != null;
        System.out.println("Success: " + (boolean) obj.get("success"));
        JSONArray dateTour = (JSONArray) obj.get("data");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd.MM.uuuu" );

        Iterator iterator = dateTour.iterator();
        List<Tour> tours = new ArrayList<>();
        Tour tour;
        while (iterator.hasNext()) {
            JSONArray jsonArray = (JSONArray) iterator.next();
            LocalDate dateFirst = LocalDate.parse( (String) jsonArray.get(0) , formatter );;
            JSONArray j1 = (JSONArray) jsonArray.get(5);
            String cityIn = (String) j1.get(0);
            JSONArray j2 = (JSONArray) jsonArray.get(16);
            String cityOut = (String) j2.get(0);
            JSONObject j3 = (JSONObject) jsonArray.get(10);
            String currency = (String) j3.get("currency");
            String priceMax = (String) j3.get("total");
            tour = new Tour(cityOut, cityIn, Integer.parseInt(priceMax), dateFirst, currency);
            tours.add(tour);
        }
        return tours;
    }
}
