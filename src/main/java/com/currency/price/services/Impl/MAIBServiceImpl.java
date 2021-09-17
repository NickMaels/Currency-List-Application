package com.currency.price.services.Impl;

import com.currency.price.model.MAIB;
import com.currency.price.parsers.nbm.ParserXML;
import com.currency.price.repositories.MAIBRepository;
import com.currency.price.services.MAIBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MAIBServiceImpl implements MAIBService {

    private final MAIBRepository maibRepository;

    private String currentDate = ParserXML.simpleDateFormat.format(new Date());

    @Override
    public void saveCurrency(List<MAIB> currencyList) {
        if (maibRepository.findByBankAndDate(currentDate).isEmpty()) {
            System.out.println(maibRepository.findByBankAndDate(currentDate).isEmpty());
            maibRepository.saveAll(currencyList);
        }
    }
}
