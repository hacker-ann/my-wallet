package wallet;

import java.io.IOException;

public abstract class Wallet {

    //adds any kind of money to the wallet and calculates the account balance
    abstract void depositMoney(double amount, String currency) throws IOException;

    //withdraws any kind of money to the wallet and calculates the account balance
    abstract void cashOutMoney(double amount, String currency) throws IOException;

    //allows exchange of currencies
    abstract void exchangeRates(double amount, String fromCurrency, String toCurrency) throws IOException;

    // display account balance
    abstract void displayAccountBalance();
}

