package wallet;

import java.io.IOException;

public class EuroWallet extends Wallet {

    private double accountBalance = 0; //account balance
    private String[] currencies = {"PLN", "EUR", "USD", "CHF"}; //table of available currencies
    private String currentCurrency = "EUR"; //the current currency in the wallet
    private double newAmount; //amount after currency exchange

    public EuroWallet(String description) {
        System.out.print("This is " + description + ". Operations are available on the following currencies: ");
        for (String currencyCode : currencies) {
            System.out.print(currencyCode + " ");
        }
        System.out.println();
    }

    public EuroWallet() {
        super();
    }

    //adds any kind of money (PLN, EUR, USD, CHF) to the wallet and calculates the account balance in EUR
    @Override
    void depositMoney(double amount, String currency) throws IOException {

        if (currency.equals("EUR")) {
            accountBalance += amount;
        } else if (currency.equals("PLN")) {
            accountBalance += ExchangeRates.exchangePLNtoEUR(amount);
        } else if (currency.equals("USD")) {
            accountBalance += ExchangeRates.exchangeUSDtoEUR(amount);
        } else if (currency.equals("CHF")) {
            accountBalance += ExchangeRates.exchangeCHFtoEUR(amount);
        } else {
            System.out.println("You can deposit PLN EUR USD CHF");
        }
    }

    //withdraws any kind of money (PLN, EUR, USD, CHF) to the wallet and calculates the account balance in EUR
    @Override
    void cashOutMoney(double amount, String currency) throws IOException {

        if (currency.equals("EUR")) {
            accountBalance -= amount;
        } else if (currency.equals("PLN")) {
            accountBalance -= ExchangeRates.exchangePLNtoEUR(amount);
        } else if (currency.equals("USD")) {
            accountBalance -= ExchangeRates.exchangeUSDtoEUR(amount);
        } else if (currency.equals("CHF")) {
            accountBalance -= ExchangeRates.exchangeCHFtoEUR(amount);
        } else {
            System.out.println("You can cash out PLN EUR USD CHF");
        }
    }

    //display exchange
    void displayExchange(double amountBefore, String fromCurrency, double amountAfter, String toCurrency) {
        System.out.format("%.2f %s %s %.2f %s", amountBefore, fromCurrency, "=", amountAfter, toCurrency);
        System.out.println("");
    }

    //converts between PLN, EUR, USD and CHF
    @Override
    void exchangeRates(double amount, String fromCurrency, String toCurrency) throws IOException {

        if (fromCurrency.equals(toCurrency)) {
            displayExchange(amount, fromCurrency, amount, toCurrency);
        } else if (fromCurrency.equals("PLN") && toCurrency.equals("EUR")) {
            newAmount = ExchangeRates.exchangePLNtoEUR(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("PLN") && toCurrency.equals("USD")) {
            newAmount = ExchangeRates.exchangePLNtoUSD(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("PLN") && toCurrency.equals("CHF")) {
            newAmount = ExchangeRates.exchangePLNtoCHF(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("PLN")) {
            newAmount = ExchangeRates.exchangeEURtoPLN(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            newAmount = ExchangeRates.exchangeEURtoUSD(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("CHF")) {
            newAmount = ExchangeRates.exchangeEURtoCHF(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("USD") && toCurrency.equals("PLN")) {
            newAmount = ExchangeRates.exchangeUSDtoPLN(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            newAmount = ExchangeRates.exchangeUSDtoEUR(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("USD") && toCurrency.equals("CHF")) {
            newAmount = ExchangeRates.exchangeUSDtoCHF(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("PLN")) {
            newAmount = ExchangeRates.exchangeCHFtoPLN(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("EUR")) {
            newAmount = ExchangeRates.exchangeCHFtoEUR(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("USD")) {
            newAmount = ExchangeRates.exchangeCHFtoUSD(amount);
            displayExchange(amount, fromCurrency, newAmount, toCurrency);
        } else {
            System.out.println("You can exchange only PLN EUR USD CHF");
        }
    }

    // display account balance
    @Override
    void displayAccountBalance() {
        System.out.format("Account balance: %.2f %s", accountBalance, currentCurrency);
        System.out.println("");
    }
}
