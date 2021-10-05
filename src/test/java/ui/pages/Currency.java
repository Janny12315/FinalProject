package ui.pages;

public enum Currency {
    USD("rel=\"5561\"", "$"),
    BYN("rel=\"533067\"", "BYN"),
    RUB("rel=\"8390\"", "₽"),
    EUR("rel=\"18864\"", "€");
    private final String link;
    private final String icon;

    Currency(String link, String icon) {
        this.link = link;
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public String getIcon() {
        return icon;
    }

    public static Currency getCurrency(String currency) {
        switch (currency) {
            case "BYN":
                return Currency.BYN;
            case "USD":
                return Currency.USD;
            case "EUR":
                return Currency.EUR;
            case "RUB":
                return Currency.RUB;
            default:
                return null;
        }
    }
}