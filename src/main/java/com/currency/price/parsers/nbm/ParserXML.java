package com.currency.price.parsers.nbm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserXML {

    public List<CurrencyNBM> getCurrencyFromXML(String date) throws IOException {

        final String url = "https://www.bnm.md/en/official_exchange_rates?get_xml=1&date=" + date;
        Document doc = Jsoup.connect(url).get();

        List<CurrencyNBM> currencyNBMList = new ArrayList<>();

        for (Element item : doc.select("Valute")) {
            CurrencyNBM currencyNBM = CurrencyNBM.builder()
                    .numCode(item.getElementsByTag("NumCode").text())
                    .charCode(item.getElementsByTag("CharCode").text())
                    .name(item.getElementsByTag("Name").text())
                    .value(Float.valueOf(item.getElementsByTag("Value").text()))
                    .build();

            currencyNBMList.add(currencyNBM);
        }
        return currencyNBMList;
    }
}
