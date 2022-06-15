package com.currency.price.services.Impl;

import com.currency.price.controllers.BankProperties;
import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.parsers.strategy.StrategyFactory;
import com.currency.price.parsers.strategy.StrategyParser;
import com.currency.price.repositories.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private StrategyFactory strategyFactory;

    @Mock
    private BankProperties bankProperties;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    private StrategyParser universalParser;

    private BankProperties.BankProperty bankProperty;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final String testDate = dateTimeFormatter.format(LocalDate.now());

    private final String TEST_BANK_NAME = "MAIB";

    private Currency currency;

    private final List<Currency> testCurrencyList = new ArrayList<>();

    private final List<String> testBankList = Collections.singletonList(TEST_BANK_NAME);

    @BeforeEach
    void init() {
        universalParser = new UniversalParser();

        bankProperty = new BankProperties.BankProperty();
        currency = Currency.builder()
                .id(0)
                .name("USD")
                .bank("MAIB")
                .buy(0.0)
                .sell(0.0)
                .date(LocalDate.now().toString())
                .build();

        testCurrencyList.add(currency);

       bankProperty.setLink("https://www.mobiasbanca.md/ru");
       bankProperty.setTag("tbody");
       bankProperty.setTitle(TEST_BANK_NAME);
    }

    @Test
    void saveCurrencyTest() throws IOException {
        when(bankProperties.getProperty(TEST_BANK_NAME)).thenReturn(bankProperty);

        when(strategyFactory.getStrategy(TEST_BANK_NAME)).thenReturn(universalParser);

        currencyService.saveCurrency(TEST_BANK_NAME);

        verify(currencyRepository, times(1)).findByBankAndDate(testDate, TEST_BANK_NAME);
    }

    @Test
    void findByBankAndDateTest() {
        when(currencyRepository.findByBankAndDate(testDate.toString(), TEST_BANK_NAME)).thenReturn(testCurrencyList);

        List<Currency> currencyList = currencyService.findByBankAndDate(testDate, currency.getBank());

        assertAll(
                () -> assertEquals(testCurrencyList.get(0).getBank(), currencyList.get(0).getBank()),
                () -> assertEquals(testCurrencyList.get(0).getName(), currencyList.get(0).getName())
        );

        verify(currencyRepository, times(1)).findByBankAndDate(testDate, TEST_BANK_NAME);
    }

    @Test
    void findAllByBankTest() {
        when(currencyRepository.findAllByBank(TEST_BANK_NAME)).thenReturn(Collections.singletonList(TEST_BANK_NAME));

        List<String> bankList = currencyService.findAllByBank(TEST_BANK_NAME);

        assertEquals(testBankList, bankList);

        verify(currencyRepository, times(1)).findAllByBank(TEST_BANK_NAME);
    }
}