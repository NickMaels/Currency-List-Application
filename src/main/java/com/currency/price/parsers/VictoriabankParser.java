package com.currency.price.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VictoriabankParser {

    public List<Currency> getCurrency(String link, String tagName) throws IOException {
        Document document = Jsoup.connect(link).get();

        String table = document.getElementsByClass(tagName).not(":first-child")
                .text();

        List<String> currencyList = Arrays.stream(table.split("(?=[A-Z]{3})"))
                .map(String::trim)
                .distinct()
                .limit(5)
                .collect(Collectors.toList());

        return currencyList.stream()
                .map(Currency::toCurrency)
                .collect(Collectors.toList());
    }
}
