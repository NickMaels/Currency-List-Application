package com.currency.price.services.Impl;

import com.currency.price.parsers.Currency;
import com.currency.price.parsers.nbm.ParserXML;
import com.currency.price.repositories.CurrencyRepository;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private String currentDate = ParserXML.simpleDateFormat.format(new Date());

    @Override
    public void saveCurrency(List<Currency> currencyList, String bankName) {
        if (currencyRepository.findByBankAndDate(currentDate, bankName).isEmpty()){
            currencyRepository.saveAll(currencyList);
        }
    }
}
