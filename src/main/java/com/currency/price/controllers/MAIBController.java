package com.currency.price.controllers;

import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.parsers.nbm.ParserXML;
import com.currency.price.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MAIBController {

    private final UniversalParser universalParser;

    private final CurrencyService currencyService;

    private final String currentDate = ParserXML.simpleDateFormat.format(new Date());

    @Value("${maib.title}")
    private String bankName;

    @RequestMapping(value = "/save/MAIB")
    public String saveCurrency(@Value("${maib.link}") String link,
                               @Value("${maib.tag}") String tagName) throws IOException {


        List<Currency> maibList = universalParser.getCurrency(link, tagName).stream()
                .peek(x -> x.setBank("MAIB"))
                .peek(x -> x.setDate(currentDate))
                .collect(Collectors.toList());


        currencyService.saveCurrency(maibList, bankName);

        return "redirect:/currency/MAIB";
    }

}
