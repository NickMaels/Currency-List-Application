package com.currency.price.services.Impl;

import com.currency.price.controllers.BankProperties;
import com.currency.price.parsers.Currency;
import com.currency.price.parsers.strategy.StrategyFactory;
import com.currency.price.parsers.strategy.StrategyParser;
import com.currency.price.repositories.CurrencyRepository;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final BankProperties bankProperties;

    private final StrategyFactory strategyFactory;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void saveCurrency(String bankName) throws IOException {
            boolean condition = currencyRepository.findByBankAndDate(dateTimeFormatter.format(LocalDate.now()), bankName)
                    .isEmpty();

            BankProperties.BankProperty properties = bankProperties.getProperty(bankName);

            StrategyParser strategyParser = strategyFactory.getStrategy(bankName);

            if (condition) {
                    strategyParser.getCurrency(properties.getLink(), properties.getTag()).stream()
                            .peek(x -> {
                                x.setBank(properties.getTitle());
                                x.setDate(dateTimeFormatter.format(LocalDate.now()));
                            })
                            .forEach(currencyRepository::save);
            }
    }

    @Override
    public List<Currency> findByBankAndDate(String date, String name) {
        return currencyRepository.findByBankAndDate(date, name);
    }

    @Override
    public List<String> findAllByBank(String name) {
        return currencyRepository.findAllByBank(name);
    }
}
