package com.currency.price.parsers;


import com.currency.price.parsers.strategy.StrategyParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UniversalParser implements StrategyParser {

    @Override
    public List<Currency> getCurrency(String link, String tagName) throws IOException {
        Document document = Jsoup.connect(link).get();

        String table = document.selectFirst(tagName).text();

        List<String> currencyList = Arrays.stream(table.split("(?=[A-Z]{3})"))
                .map(String::trim)
                .collect(Collectors.toList());

        return currencyList.stream()
                .map(Currency::toCurrency)
                .collect(Collectors.toList());
    }

    @Override
    public String getStrategyName() {
        return "default";
    }
}
