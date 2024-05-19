package com.currency.price.parsers;

import com.currency.price.model.Currency;
import com.currency.price.parsers.strategy.StrategyParser;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MICBParser implements StrategyParser {
    @Override
    public List<Currency> getCurrency(String link, String tagName) throws IOException {
        Document document = Jsoup.connect(link).get();

        Elements rows = document.select(tagName);

        return rows.stream()
                .map(row -> row.select(".col"))
                .map(cols -> new Currency(
                        cols.attr("class").replaceAll("[^A-Z]+", Strings.EMPTY),
                        cols.get(0).text(),
                        cols.get(1).text()))
                .limit(7)
                .toList();
    }

    @Override
    public String getStrategyName() {
        return "MICB";
    }
}
