package com.currency.price.controllers;


import com.currency.price.parsers.Currency;
import com.currency.price.parsers.UniversalParser;
import com.currency.price.parsers.nbm.CurrencyNBM;
import com.currency.price.parsers.nbm.ParserXML;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/currency")
@RequiredArgsConstructor
public class MainController {

    private final UniversalParser universalParser;

    private final ParserXML parserXML;

    @GetMapping("/home")
    public String homePage(Model model) throws IOException {

        List<CurrencyNBM> currencyList = parserXML.getCurrencyFromXML(new Date());

        model.addAttribute("date", ParserXML.simpleDateFormat.format(new Date()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", "NBM");

        return "home";
    }

    @GetMapping("/MICB")
    public String getCurrencyMICB(Model model,
                                  @Value("${micb.link}")String link,
                                  @Value("${micb.tag}")String tagName) throws IOException {

        List<Currency> currencyList = universalParser.getCurrency(link, tagName);

        model.addAttribute("date", ParserXML.simpleDateFormat.format(new Date()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", "Moldindconbank");

        return "currencyPage";
    }

    @GetMapping("/MAIB")
    public String getCurrencyMAIB(Model model,
                                  @Value("${maib.link}")String link,
                                  @Value("${maib.tag}")String tagName) throws IOException {

        List<Currency> currencyList = universalParser.getCurrency(link, tagName);

        model.addAttribute("date", ParserXML.simpleDateFormat.format(new Date()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", "MAIB");

        return "currencyPage";
    }

    @GetMapping("/Mobiasbanca")
    public String getCurrencyMobias(Model model,
                                  @Value("${mobias.link}")String link,
                                  @Value("${mobias.tag}")String tagName) throws IOException {

        List<Currency> currencyList = universalParser.getCurrency(link, tagName);

        model.addAttribute("date", ParserXML.simpleDateFormat.format(new Date()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", "Mobiasbanca");

        return "currencyPage";
    }

    @GetMapping("/Victoriabank")
    public String getCurrencyVictoria(Model model,
                                    @Value("${victoria.link}")String link,
                                    @Value("${victoria.tag}")String tagName) throws IOException {

        List<Currency> currencyList = universalParser.getCurrency(link, tagName);

        model.addAttribute("date", ParserXML.simpleDateFormat.format(new Date()));

        model.addAttribute("currencyList", currencyList);

        model.addAttribute("bankName", "Victoriabank");

        return "currencyPage";
    }
}
