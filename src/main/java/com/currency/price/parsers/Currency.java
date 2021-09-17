package com.currency.price.parsers;

import com.currency.price.model.MAIB;
import com.currency.price.model.MICB;
import com.currency.price.parsers.nbm.ParserXML;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder

@Entity
@Table(name = "currency")
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double buy;

    private Double sell;

    private String date;

    private String bank;

    public static Currency toCurrency(String string){
        List<String> list = Arrays.stream(string.split(" "))
                .collect(Collectors.toList());

        return Currency.builder()
                .name(list.get(0))
                .buy(Double.parseDouble(list.get(1)))
                .sell(Double.parseDouble(list.get(2)))
                .build();
    }

    public static MAIB toEntity(Currency currency){
        return MAIB.builder()
                .currency(currency.getName())
                .buy(currency.getBuy())
                .sell(currency.getSell())
                .date(ParserXML.simpleDateFormat.format(new Date()))
                .bank("MAIB")
                .build();
    }

    public static MICB toEntity2(Currency currency){
        return MICB.builder()
                .currency(currency.getName())
                .buy(currency.getBuy())
                .sell(currency.getSell())
                .date(ParserXML.simpleDateFormat.format(new Date()))
                .bank("MICB")
                .build();
    }
}
