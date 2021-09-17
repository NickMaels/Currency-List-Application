package com.currency.price.services;

import com.currency.price.parsers.Currency;

import java.util.List;

public interface CurrencyService {
    void saveCurrency(List<Currency> currencyList);
}
