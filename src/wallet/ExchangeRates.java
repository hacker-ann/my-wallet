package wallet;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ExchangeRates {
    static double exchangeRateEUR;
    static double exchangeRateUSD;
    static double exchangeRateCHF;

    //downloading the Euro exchange rate in XML format from the NBP
    static double downloadExchangeRateEUR() throws IOException {
        String URL = "http://api.nbp.pl/api/exchangerates/rates/a/eur/";
        Connection connect = Jsoup.connect(URL);
        Document document = connect.ignoreContentType(true).get();
        Element body = document.select("body").first();
        String xmlContent = body.text();
        String exchangeRateEUR = xmlContent.substring(xmlContent.indexOf("mid") + 5, xmlContent.indexOf("mid") + 11);
        return Double.parseDouble(exchangeRateEUR);
    }

    //downloading the USD exchange rate in XML format from the NBP
    static double downloadExchangeRateUSD() throws IOException {
        String URL = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";
        Connection connect = Jsoup.connect(URL);
        Document document = connect.ignoreContentType(true).get();
        Element body = document.select("body").first();
        String xmlContent = body.text();
        String exchangeRateUSD = xmlContent.substring(xmlContent.indexOf("mid") + 5, xmlContent.indexOf("mid") + 11);
        return Double.parseDouble(exchangeRateUSD);
    }

    //downloading the CHF exchange rate in XML format from the NBP
    static double downloadExchangeRateCHF() throws IOException {
        String URL = "http://api.nbp.pl/api/exchangerates/rates/a/chf/";
        Connection connect = Jsoup.connect(URL);
        Document document = connect.ignoreContentType(true).get();
        Element body = document.select("body").first();
        String xmlContent = body.text();
        String exchangeRateCHF = xmlContent.substring(xmlContent.indexOf("mid") + 5, xmlContent.indexOf("mid") + 11);
        return Double.parseDouble(exchangeRateCHF);
    }

    // Currency Exchange
    static double exchangePLNtoEUR(double amount) throws IOException {
        exchangeRateEUR = ExchangeRates.downloadExchangeRateEUR();
        return amount / exchangeRateEUR;
    }

    static double exchangePLNtoUSD(double amount) throws IOException {
        exchangeRateUSD = ExchangeRates.downloadExchangeRateUSD();
        return amount / exchangeRateUSD;
    }

    static double exchangePLNtoCHF(double amount) throws IOException {
        exchangeRateCHF = ExchangeRates.downloadExchangeRateCHF();
        return amount / exchangeRateCHF;
    }

    static double exchangeEURtoPLN(double amount) throws IOException {
        exchangeRateEUR = ExchangeRates.downloadExchangeRateEUR();
        return amount * exchangeRateEUR;
    }

    static double exchangeEURtoUSD(double amount) throws IOException {
        exchangeRateEUR = ExchangeRates.downloadExchangeRateEUR();
        exchangeRateUSD = ExchangeRates.downloadExchangeRateUSD();
        return (amount * exchangeRateEUR) / exchangeRateUSD;
    }

    static double exchangeEURtoCHF(double amount) throws IOException {
        exchangeRateEUR = ExchangeRates.downloadExchangeRateEUR();
        exchangeRateCHF = ExchangeRates.downloadExchangeRateCHF();
        return (amount * exchangeRateEUR) / exchangeRateCHF;
    }

    static double exchangeUSDtoPLN(double amount) throws IOException {
        exchangeRateUSD = ExchangeRates.downloadExchangeRateUSD();
        return amount * exchangeRateUSD;
    }

    static double exchangeUSDtoEUR(double amount) throws IOException {
        exchangeRateEUR = ExchangeRates.downloadExchangeRateEUR();
        exchangeRateUSD = ExchangeRates.downloadExchangeRateUSD();
        return (amount * exchangeRateUSD) / exchangeRateEUR;
    }

    static double exchangeUSDtoCHF(double amount) throws IOException {
        exchangeRateUSD = ExchangeRates.downloadExchangeRateUSD();
        exchangeRateCHF = ExchangeRates.downloadExchangeRateCHF();
        return (amount * exchangeRateUSD) / exchangeRateCHF;
    }

    static double exchangeCHFtoPLN(double amount) throws IOException {
        exchangeRateCHF = ExchangeRates.downloadExchangeRateCHF();
        return amount * exchangeRateCHF;
    }

    static double exchangeCHFtoEUR(double amount) throws IOException {
        exchangeRateEUR = ExchangeRates.downloadExchangeRateEUR();
        exchangeRateCHF = ExchangeRates.downloadExchangeRateCHF();
        return (amount * exchangeRateCHF) / exchangeRateEUR;
    }

    static double exchangeCHFtoUSD(double amount) throws IOException {
        exchangeRateUSD = ExchangeRates.downloadExchangeRateUSD();
        exchangeRateCHF = ExchangeRates.downloadExchangeRateCHF();
        return (amount * exchangeRateCHF) / exchangeRateUSD;
    }
}
