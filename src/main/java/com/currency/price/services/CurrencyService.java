package com.currency.price.services;

import com.currency.price.parsers.Currency;

import java.io.IOException;
import java.util.List;

public interface CurrencyService {
    void saveCurrency(String bankName) throws IOException;

    List<Currency> findByBankAndDate(String date, String name);

    List<String> findAllByBank(String name);
}
