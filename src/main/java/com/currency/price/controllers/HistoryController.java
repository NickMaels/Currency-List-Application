package com.currency.price.controllers;

import com.currency.price.parsers.Currency;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/story")
@RequiredArgsConstructor
public class HistoryController {

    private final CurrencyService currencyService;

    private final BankProperties bankProperties;

    @GetMapping("/{bankName}")
    public String getDateList(Model model, @PathVariable("bankName") String bankName) {

        BankProperties.BankProperty properties = bankProperties.getProperty(bankName);

        List<String> dateList = currencyService.findAllByBank(properties.getTitle());

        model.addAttribute("dateList", dateList);

        return "historyPage";
    }

    @PostMapping("/{bankName}")
    public String getCurrencyByDate(Model model, @PathVariable("bankName") String bankName,
                                    @RequestParam("date") String date) {

        BankProperties.BankProperty properties = bankProperties.getProperty(bankName);

        List<Currency> currencyList = currencyService.findByBankAndDate(date, properties.getTitle());

        List<String> dateList = currencyService.findAllByBank(properties.getTitle());

        model.addAttribute("dateList", dateList);

        model.addAttribute("currencyList", currencyList);

        return "historyPage";
    }

}
