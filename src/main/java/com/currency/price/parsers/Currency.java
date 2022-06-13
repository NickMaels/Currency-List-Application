package com.currency.price.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double buy;

    private Double sell;

    private String date;

    private String bank;

    public static Currency toCurrency(String string) {
        List<String> list = Arrays.stream(string.split(" "))
                .collect(Collectors.toList());
        try {
            return getCurrency(list.get(0), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(2)));
        } catch (NumberFormatException nfe) {
            return getCurrency(list.get(0), 0.0, 0.0);
        }
    }

    private static Currency getCurrency(String name, Double buy, Double sell) {
        return Currency.builder()
                .name(name)
                .buy(buy)
                .sell(sell)
                .build();
    }
}
