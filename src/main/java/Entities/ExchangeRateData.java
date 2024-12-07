package Entities;

import java.util.Map;

public class ExchangeRateData {
    private String codeBase;
    private Map<String, Double> conversion_rates;

    public String getBaseCode() {
        return codeBase;
    }

    public void setBaseCode(String base_code) {
        this.codeBase = base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
