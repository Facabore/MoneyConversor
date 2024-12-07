package Entities;

public class Currency {
    private String currency_code;
    private String country;

    public Currency(String code, String country) {
        this.currency_code = code;
        this.country = country;
    }

    @Override
    public String toString() {
        return country + " - " + currency_code;
    }
}
