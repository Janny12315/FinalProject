package api.pages;

import java.time.LocalDate;
import java.util.Date;

public class Tour {
    private final String cityFrom;
    private final String cityIn;
    private final int priceMax;
    private final LocalDate date;
    private final String currency;

    public Tour(String cityFrom, String cityIn, int priceMax, LocalDate date, String dateLast) {
        this.cityFrom = cityFrom;
        this.cityIn = cityIn;
        this.priceMax = priceMax;
        this.date = date;
        this.currency = dateLast;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "cityFrom='" + cityFrom + '\'' +
                ", cityIn='" + cityIn + '\'' +
                ", priceMax='" + priceMax + '\'' +
                ", date='" + date + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}

