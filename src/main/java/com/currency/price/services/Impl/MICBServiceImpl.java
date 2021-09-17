package com.currency.price.services.Impl;

import com.currency.price.model.MICB;
import com.currency.price.parsers.nbm.ParserXML;
import com.currency.price.repositories.MICBRepository;
import com.currency.price.services.MICBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MICBServiceImpl implements MICBService {

    private final MICBRepository micbRepository;

    private String currentDate = ParserXML.simpleDateFormat.format(new Date());

    @Override
    public void saveCurrency(List<MICB> currencyList) {
        if (micbRepository.findByBankAndDate(currentDate).isEmpty()){
            micbRepository.saveAll(currencyList);
        }
    }
}
