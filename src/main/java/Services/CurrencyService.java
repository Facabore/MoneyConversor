package Services;

import com.google.gson.Gson;
import Entities.ExchangeRateData;
import Helpers.LoadApi;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyService {
    private static final String BASE_URL = " https://v6.exchangerate-api.com/v6/";

    private static final String API_URL = LoadApi.loadProperties();

    /**
     * Obtiene las tasas de cambio desde la API ExchangeRate-API.
     *
     * @return Un objeto ExchangeRateData con las tasas de cambio.
     */
    public ExchangeRateData fetchExchangeRates() {
        try {
            URL url = new URL(BASE_URL+API_URL+"/latest/USD");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();
                ExchangeRateData data = gson.fromJson(reader, ExchangeRateData.class);
                reader.close();
                return data;
            }
            if (connection.getResponseCode() == 404) {
                throw new RuntimeException("Something is wrong with the API");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
