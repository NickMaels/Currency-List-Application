package com.currency.price.parsers;

import io.vavr.control.Try;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class Currency {
    String name;

    Double buy;

    Double sell;

    public static Currency toCurrency(String string){
        List<String> list = Arrays.stream(string.split(" "))
                .collect(Collectors.toList());

        return Currency.builder()
                .name(list.get(0))
                .buy(Double.parseDouble(list.get(1)))
                .sell(Double.parseDouble(list.get(2)))
                .build();
    }
}
