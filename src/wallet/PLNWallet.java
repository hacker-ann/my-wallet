package wallet;

import java.io.IOException;
import java.util.ArrayList;

public class PLNWallet extends Wallet {
    private double accountBalance = 0; //account balance
    private String[] currencies = {"PLN", "EUR", "USD", "CHF"}; //table of available currencies
    private String currentCurrency = "PLN"; //the current currency in the wallet
    ArrayList depositEURlist = new ArrayList();
    ArrayList cashOutEURlist = new ArrayList();
    ArrayList depositPLNlist = new ArrayList();
    ArrayList cashOutPLNlist = new ArrayList();
    ArrayList depositUSDlist = new ArrayList();
    ArrayList cashOutUSDlist = new ArrayList();
    ArrayList depositCHFlist = new ArrayList();
    ArrayList cashOutCHFlist = new ArrayList();


    public PLNWallet(String description) {
        System.out.print("This is " + description + ". Operations are available on the following currencies: ");
        for (String currencyCode : currencies) {
            System.out.print(currencyCode + " ");
        }
        System.out.println();
    }

    public PLNWallet() {
        super();
    }

    //adds any kind of money (PLN, EUR, USD, CHF) to the wallet and calculates the account balance in PLN
    @Override
    void depositMoney(double amount, String currency) throws IOException {
        if (currency.equals("PLN")) {
            accountBalance += amount;
            depositPLNlist.add(amount);
        } else if (currency.equals("USD")) {
            accountBalance += ExchangeRates.exchangeUSDtoPLN(amount);
            depositUSDlist.add(amount);
        } else if (currency.equals("EUR")) {
            accountBalance += ExchangeRates.exchangeEURtoPLN(amount);
            depositEURlist.add(amount);
        } else if (currency.equals("CHF")) {
            accountBalance += ExchangeRates.exchangeCHFtoPLN(amount);
            depositCHFlist.add(amount);
        } else {
            System.out.println("You can deposit PLN EUR USD CHF");
        }
    }

    //withdraws any kind of money (PLN, EUR, USD, CHF) to the wallet and calculates the account balance in PLN
    @Override
    void cashOutMoney(double amount, String currency) throws IOException {
        if (currency.equals("PLN")) {
            accountBalance -= amount;
            cashOutPLNlist.add(amount * (-1));
        } else if (currency.equals("USD")) {
            accountBalance -= ExchangeRates.exchangeUSDtoPLN(amount);
            cashOutUSDlist.add(amount * (-1));
        } else if (currency.equals("EUR")) {
            accountBalance -= ExchangeRates.exchangeEURtoPLN(amount);
            cashOutEURlist.add(amount * (-1));
        } else if (currency.equals("CHF")) {
            accountBalance -= ExchangeRates.exchangeCHFtoPLN(amount);
            cashOutCHFlist.add(amount * (-1));
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

    //display account balance
    @Override
    void displayAccountBalance() {
        System.out.format("Account balance: %.2f %s", accountBalance, currentCurrency);
        System.out.println("");
    }

    /*join list of operations
ArrayList joinOperationLists (ArrayList ... list) {
        ArrayList joinList = new ArrayList(0);
    for (ArrayList element : list){
        {
            joinList.addAll(element);
        }
    }
        return joinList;
    }
    */

    void displayFormatedList(ArrayList list, String currency) {
        for (Object element : list) {
            System.out.format(" %.2f %s", element, currency);
            System.out.println("");
        }
    }

    // // display selected type of operations
    void displayOperations(String operation, String currency) {
        if (operation.equals("cash in") && currency.equals("PLN")) {
            displayFormatedList(depositPLNlist, currency);
        } else if (operation.equals("cash out") && currency.equals("PLN")) {
            displayFormatedList(cashOutPLNlist, currency);
        } else if (operation.equals("all") && currency.equals("PLN")) {
            displayFormatedList(depositPLNlist, currency);
            displayFormatedList(cashOutPLNlist, currency);
        } else if (operation.equals("cash in") && currency.equals("EUR")) {
            displayFormatedList(depositEURlist, currency);
        } else if (operation.equals("cash out") && currency.equals("EUR")) {
            displayFormatedList(cashOutEURlist, currency);
        } else if (operation.equals("all") && currency.equals("EUR")) {
            displayFormatedList(depositEURlist, currency);
            displayFormatedList(cashOutEURlist, currency);
        } else if (operation.equals("cash in") && currency.equals("USD")) {
            displayFormatedList(depositUSDlist, currency);
        } else if (operation.equals("cash out") && currency.equals("USD")) {
            displayFormatedList(cashOutUSDlist, currency);
        } else if (operation.equals("all") && currency.equals("USD")) {
            displayFormatedList(depositUSDlist, currency);
            displayFormatedList(cashOutUSDlist, currency);
        } else if (operation.equals("cash in") && currency.equals("CHF")) {
            displayFormatedList(depositCHFlist, currency);
        } else if (operation.equals("cash out") && currency.equals("CHF")) {
            displayFormatedList(cashOutCHFlist, currency);
        } else if (operation.equals("all") && currency.equals("CHF")) {
            displayFormatedList(depositCHFlist, currency);
            displayFormatedList(cashOutCHFlist, currency);
        } else if (operation.equals("cash in") && currency.equals("all")) {
            displayFormatedList(depositPLNlist, "PLN");
            displayFormatedList(depositEURlist, "EUR");
            displayFormatedList(depositUSDlist, "USD");
            displayFormatedList(depositCHFlist, "CHF");
        } else if (operation.equals("cash out") && currency.equals("all")) {
            displayFormatedList(cashOutPLNlist, "PLN");
            displayFormatedList(cashOutEURlist, "EUR");
            displayFormatedList(cashOutUSDlist, "USD");
            displayFormatedList(cashOutCHFlist, "CHF");
        } else if (operation.equals("all") && currency.equals("all")) {
            displayFormatedList(depositPLNlist, "PLN");
            displayFormatedList(cashOutPLNlist, "PLN");
            displayFormatedList(depositEURlist, "EUR");
            displayFormatedList(cashOutEURlist, "EUR");
            displayFormatedList(depositUSDlist, "USD");
            displayFormatedList(cashOutUSDlist, "USD");
            displayFormatedList(depositCHFlist, "CHF");
            displayFormatedList(cashOutCHFlist, "CHF");
        } else {
            System.out.println("Invalid operation or currency");
        }
    }
}



