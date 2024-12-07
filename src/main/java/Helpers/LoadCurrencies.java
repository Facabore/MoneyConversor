package Helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Entities.Currency;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class LoadCurrencies {

    public static List<Currency> loadJsonCurrencies() {
        Gson gson = new Gson();
        String path = "src/main/resources/currencies.json";
        try (FileReader reader = new FileReader(path)) {
            Type currencyListType = new TypeToken<List<Currency>>() {}.getType();

            List<Currency> currencies = gson.fromJson(reader, currencyListType);

            return currencies;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}