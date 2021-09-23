package com.currency.price.controllers;


import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/save")
@RequiredArgsConstructor
public class SaveController {
    private final UniversalParser universalParser;

    private final CurrencyService currencyService;

    private final BankProperties bankProperties;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @RequestMapping(value = "/{bankName}")
    public String saveCurrency(Model model, @PathVariable("bankName") String bankName) throws IOException {

        BankProperties.BankProperty properties = bankProperties.getProperty(bankName);

        List<Currency> currencyList = universalParser.getCurrency(properties.getLink(), properties.getTag()).stream()
                .peek(x -> x.setBank(properties.getTitle()))
                .peek(x -> x.setDate(dateTimeFormatter.format(LocalDate.now())))
                .collect(Collectors.toList());

        currencyService.saveCurrency(currencyList, properties.getTitle());

        model.addAttribute("bankName", properties.getTitle());

        return "redirect:/currency/" + properties.getTitle();
    }
}