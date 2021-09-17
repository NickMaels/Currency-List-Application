package com.currency.price.controllers;


import com.currency.price.model.MICB;
import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.services.MICBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MICBController {

    private final UniversalParser universalParser;

    private final MICBService micbService;

    @RequestMapping(value = "/save/MICB")
    public String saveCurrency(@Value("${micb.link}") String link,
                               @Value("${micb.tag}") String tagName) throws IOException {


        List<MICB> micbList = universalParser.getCurrency(link, tagName).stream()
                .map(Currency::toEntity2)
                .collect(Collectors.toList());


        micbService.saveCurrency(micbList);

        return "redirect:/currency/MICB";
    }
}
