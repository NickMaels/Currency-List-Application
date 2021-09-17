package com.currency.price.services;

import com.currency.price.model.MAIB;

import java.util.List;

public interface MAIBService {
    void saveCurrency(List<MAIB> currencyList);
}
