import Helpers.LoadCurrencies;
import Entities.Currency;
import Entities.ExchangeRateData;
import Services.CurrencyService;

import java.util.*;

public class Main {
    private static final List<String> conversionHistory = new ArrayList<>();
    private static final Map<String, List<String>> continentCurrencies = new HashMap<>();

    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();
        ExchangeRateData exchangeRateData = currencyService.fetchExchangeRates();


        Map<String, Double> rates = exchangeRateData.getConversionRates();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            showMenu();
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea que queda en el buffer

            switch (option) {
                case 1 -> convertCurrency(rates, scanner);
                case 2 -> showCurrencies(scanner);
                case 3 -> showHistory(scanner);
                case 4 -> {
                    System.out.println("\n¡Gracias por usar el conversor de monedas!");
                    return;
                }
                default -> System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n+----------------------------+");
        System.out.println("|     CONVERSOR DE MONEDAS   |");
        System.out.println("+----------------------------+");
        System.out.println("| 1. Conversión de monedas   |");
        System.out.println("| 2. Ver listado de monedas  |");
        System.out.println("| 3. Historial de conversiones|");
        System.out.println("| 4. Salir                   |");
        System.out.println("+----------------------------+");
    }

    private static void convertCurrency(Map<String, Double> rates, Scanner scanner) {
        while (true) {
            System.out.println("\n+-------------------------------+");
            System.out.println("|   === Conversión de Monedas === |");
            System.out.println("+-------------------------------+");
            System.out.print("| Ingrese la moneda de origen (por ejemplo, USD): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("| Ingrese la moneda de destino (por ejemplo, EUR): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("| Ingrese la cantidad a convertir: ");
            String input = scanner.nextLine();

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("| Cantidad inválida. Intente nuevamente.");
                continue;
            }

            if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
                System.out.println("| Una o ambas monedas no son válidas. Intente nuevamente.");
                continue;
            }

            double fromRate = rates.get(fromCurrency);
            double toRate = rates.get(toCurrency);

            double convertedAmount = (amount / fromRate) * toRate;

            System.out.printf("| %.2f %s equivalen a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);

            String record = String.format("%.2f %s -> %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
            conversionHistory.add(record);

            System.out.println("\n| Desea realizar una nueva conversión? (S/N)");
            String userChoice = scanner.nextLine().toUpperCase();
            if (userChoice.equals("N")) {
                return; // Regresar al menú principal
            }
        }
    }

    private static void showHistory(Scanner scanner) {
        System.out.println("\n+------------------------------------+");
        System.out.println("|   === Historial de Conversiones === |");
        System.out.println("+------------------------------------+");
        if (conversionHistory.isEmpty()) {
            System.out.println("| No hay conversiones anteriores.");
        } else {
            for (String record : conversionHistory) {
                System.out.println("| " + record);
            }
        }
        System.out.println("+------------------------------------+");
        scanner.nextLine();
    }


    private static void showCurrencies(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.println("\n+-----------------------------+");
        System.out.println("| === Monedas por Continente === |");
        System.out.println("+-----------------------------+");
        List<Currency> currencies = LoadCurrencies.loadJsonCurrencies();
        if(currencies == null || currencies.isEmpty()) {
            throw new RuntimeException("No se ha encontrado ninguna moneda.");
        }
        for (Currency currency : currencies){
            System.out.println(currency);
        }
        System.out.println("\n+-----------------------------+");
        scanner.nextLine();
    }
}
