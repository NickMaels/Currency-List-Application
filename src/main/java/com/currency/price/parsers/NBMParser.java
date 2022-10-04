package com.currency.price.parsers;

import com.currency.price.parsers.strategy.StrategyParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NBMParser implements StrategyParser {
    @Override
    public List<Currency> getCurrency(String link, String date) throws IOException {
        Document document = Jsoup.connect(link.concat(date)).get();

        return document.select("Valute").stream()
                .map(item -> Currency.builder()
                        .name(item.getElementsByTag("Name").text())
                        .buy(Double.parseDouble(item.getElementsByTag("Value").text()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public String getStrategyName() {
        return "NBM";
    }
}
