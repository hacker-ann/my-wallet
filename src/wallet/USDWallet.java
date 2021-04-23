package wallet;

import java.io.IOException;
import java.util.ArrayList;

public class USDWallet extends Wallet {

    private double accountBalance = 0; //account balance
    private String[] currencies = {"PLN", "EUR", "USD", "CHF"}; //table of available currencies
    private String currentCurrency = "USD"; //the current currency in the wallet
    protected ArrayList allUsdOperations = new ArrayList();

    public USDWallet(String description) {
        System.out.print("This is " + description + ". Operations are available on the following currencies: ");
        for (String currencyCode : currencies) {
            System.out.print(currencyCode + " ");
        }
        System.out.println();
    }

    public USDWallet() {
        super();
    }

    //adds any kind of money (PLN, EUR, USD, CHF) to the wallet and calculates the account balance in USD
    @Override
    void depositMoney(double amount, String currency) throws IOException {

        if (currency.equals("USD")) {
            accountBalance += amount;
            allUsdOperations.add(amount + " " + currency);
        } else if (currency.equals("PLN")) {
            accountBalance += ExchangeRates.exchangePLNtoUSD(amount);
            allUsdOperations.add(amount + " " + currency);
        } else if (currency.equals("EUR")) {
            accountBalance += ExchangeRates.exchangeEURtoUSD(amount);
            allUsdOperations.add(amount + " " + currency);
        } else if (currency.equals("CHF")) {
            accountBalance += ExchangeRates.exchangeCHFtoUSD(amount);
            allUsdOperations.add(amount + " " + currency);
        } else {
            System.out.println("You can deposit PLN EUR USD CHF");
        }
    }

    //withdraws any kind of money (PLN, EUR, USD, CHF) to the wallet and calculates the account balance in USD
    @Override
    void cashOutMoney(double amount, String currency) throws IOException {
        if (currency.equals("USD")) {
            accountBalance -= amount;
            allUsdOperations.add((-1) * amount + " " + currency);
        } else if (currency.equals("PLN")) {
            accountBalance -= ExchangeRates.exchangePLNtoUSD(amount);
            allUsdOperations.add((-1) * amount + " " + currency);
        } else if (currency.equals("EUR")) {
            accountBalance -= ExchangeRates.exchangeEURtoUSD(amount);
            allUsdOperations.add((-1) * amount + " " + currency);
        } else if (currency.equals("CHF")) {
            accountBalance -= ExchangeRates.exchangeCHFtoUSD(amount);
            allUsdOperations.add((-1) * amount + " " + currency);
        } else {
            System.out.println("You can cash out PLN EUR USD CHF");
        }
    }

    //converts between PLN, EUR, USD and CHF
    @Override
    void exchangeRates(double amount, String fromCurrency, String toCurrency) throws IOException {
        EuroWallet w = new EuroWallet();
        w.exchangeRates(amount, fromCurrency, toCurrency);
    }

    // display account balance
    @Override
    void displayAccountBalance() {
        System.out.format("Account balance: %.2f %s", accountBalance, currentCurrency);
        System.out.println("");
    }

    //  dispaly all operations
    void displayAllOperations(ArrayList list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }
}
