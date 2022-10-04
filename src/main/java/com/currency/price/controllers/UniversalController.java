package com.currency.price.controllers;

import com.currency.price.parsers.Currency;
import com.currency.price.parsers.strategy.StrategyFactory;
import com.currency.price.parsers.strategy.StrategyParser;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/currency")
@RequiredArgsConstructor
public class UniversalController {

    private final StrategyFactory strategyFactory;

    private final BankProperties bankProperties;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final CurrencyService currencyService;

    @GetMapping("/home")
    public String homePage(Model model) throws IOException {

        List<Currency> currencyList = strategyFactory.getStrategy("NBM")
                .getCurrency(bankProperties.getProperty("NBM").getLink(), dateTimeFormatter.format(LocalDate.now()));

        model.addAttribute("date", dateTimeFormatter.format(LocalDate.now()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", "NBM");

        return "home";
    }

    @GetMapping("/{bankName}")
    public String getCurrency(Model model, @PathVariable("bankName") String bankName) throws IOException {

        BankProperties.BankProperty properties = bankProperties.getProperty(bankName);

        StrategyParser strategyParser = strategyFactory.getStrategy(bankName);

        List<Currency> currencyList = strategyParser.getCurrency(properties.getLink(), properties.getTag());

        currencyService.saveCurrency(bankName);

        model.addAttribute("date", dateTimeFormatter.format(LocalDate.now()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", properties.getTitle());

        return "currencyPage";
    }
}
