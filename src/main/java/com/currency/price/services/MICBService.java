package com.currency.price.services;

import com.currency.price.model.MICB;

import java.util.List;

public interface MICBService {
    void saveCurrency(List<MICB> currencyList);
}
