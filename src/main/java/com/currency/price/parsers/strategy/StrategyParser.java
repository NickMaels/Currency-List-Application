package com.currency.price.parsers.strategy;

import com.currency.price.model.Currency;

import java.io.IOException;
import java.util.List;

public interface StrategyParser {
    List<Currency> getCurrency(String link, String tagName) throws IOException;

    String getStrategyName();
}
