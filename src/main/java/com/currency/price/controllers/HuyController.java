package com.currency.price.controllers;

import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.parsers.nbm.ParserXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("huy")
@RequiredArgsConstructor
public class HuyController {

    private final BankProperties bankProperties;

    private final UniversalParser universalParser;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @RequestMapping("info")
    public String info(@RequestParam("bank") String bank, Model model) throws IOException {
        BankProperties.BankProperty properties = bankProperties.getProperty(bank);
        List<Currency> currencyList = universalParser.getCurrency(properties.getLink(), properties.getTag());

        model.addAttribute("date", dateTimeFormatter.format(LocalDate.now()));
        model.addAttribute("currencyList", currencyList);
        model.addAttribute("bankName", properties.getTitle());

        return "MICB";
    }


//
//    List<Currency> currencyList = universalParser.getCurrency(link, tagName);
//
//        model.addAttribute("date", ParserXML.simpleDateFormat.format(new Date()));
//
//        model.addAttribute("currencyList", currencyList);
//
//        model.addAttribute("bankName", "MICB");
//
//        return "MICB";

}
