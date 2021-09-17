package com.currency.price.controllers;

import com.currency.price.model.MAIB;
import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.services.MAIBService;
import com.currency.price.services.MICBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MAIBController {

    private final UniversalParser universalParser;

    private final MAIBService maibService;

    @RequestMapping(value = "/save/MAIB")
    public String saveCurrency(@Value("${maib.link}") String link,
                               @Value("${maib.tag}") String tagName) throws IOException {


        List<MAIB> maibList = universalParser.getCurrency(link, tagName).stream()
                .map(Currency::toEntity)
                .collect(Collectors.toList());


        maibService.saveCurrency(maibList);

        return "redirect:/currency/MAIB";
    }

}
