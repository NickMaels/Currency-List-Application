package com.currency.price.services.Impl;

import com.currency.price.parsers.Currency;
import com.currency.price.repositories.CurrencyRepository;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void saveCurrency(List<Currency> currencyList, String bankName) {
        if (currencyRepository.findByBankAndDate(dateTimeFormatter.format(LocalDate.now()), bankName).isEmpty()){
            currencyRepository.saveAll(currencyList);
        }
    }
}
