package api.connect;

public class StringRequest {

    private String newStringRequest;

    public StringRequest() {
        newStringRequest = "https://search.tez-tour.com/tariffsearch/getResult?";
    }

    public StringRequest withPriceMin(int priceMin) {
        newStringRequest = newStringRequest + String.format("priceMin=%d&", priceMin);
        return this;
    }

    public StringRequest withPriceMax(int priceMax) {
        newStringRequest = newStringRequest + String.format("priceMax=%d&", priceMax);
        return this;
    }

    public StringRequest withNightsMin(int nightsMin) {
        newStringRequest = newStringRequest + String.format("nightsMin=%d&", nightsMin);
        return this;
    }

    public StringRequest withNightsMax(int nightsMax) {
        newStringRequest = newStringRequest + String.format("nightsMax=%d&", nightsMax);
        return this;
    }

    public StringRequest withDayAfter(String after) {
        newStringRequest = newStringRequest + String.format("after=%s&", after);
        return this;
    }

    public StringRequest withSDayBefore(String before) {
        newStringRequest = newStringRequest + String.format("before=%s&", before);
        return this;
    }

    public StringRequest withCountryId(int countryId) {
        newStringRequest = newStringRequest + String.format("countryId=%d&", countryId);
        return this;
    }

    public StringRequest withCityId(int cityId) {
        newStringRequest = newStringRequest + String.format("cityId=%d&", cityId);
        return this;
    }

    public StringRequest withHotelClassId(int hotelClassId) {
        newStringRequest = newStringRequest + String.format("hotelClassId=%d&", hotelClassId);
        return this;
    }

    public StringRequest withAccommodationId(int accommodationId) {
        newStringRequest = newStringRequest + String.format("accommodationId=%d&", accommodationId);
        return this;
    }

    public StringRequest withRAndBId(int rAndBId) {
        newStringRequest = newStringRequest + String.format("rAndBId=%d&", rAndBId);
        return this;
    }

    public String getStringRequest() {
        return newStringRequest;
    }

}
