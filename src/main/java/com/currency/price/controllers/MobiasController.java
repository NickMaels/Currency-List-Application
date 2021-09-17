package com.currency.price.controllers;

import com.currency.price.model.MICB;
import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.parsers.nbm.ParserXML;
import com.currency.price.repositories.CurrencyRepository;
import com.currency.price.services.MICBService;
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
public class MobiasController {

    private final UniversalParser universalParser;

    private final CurrencyRepository currencyRepository;

    private final String currentDate = ParserXML.simpleDateFormat.format(new Date());

    @RequestMapping(value = "/save/Mobiasbanca")
    public String saveCurrency(@Value("${mobias.link}") String link,
                               @Value("${mobias.tag}") String tagName) throws IOException {


        List<Currency> currencyList = universalParser.getCurrency(link, tagName).stream()
                .peek(x -> x.setBank("Mobiasbanca"))
                .peek(x -> x.setDate(currentDate))
                .collect(Collectors.toList());

        currencyRepository.saveAll(currencyList);

        return "redirect:/currency/Mobiasbanca";
    }
}
