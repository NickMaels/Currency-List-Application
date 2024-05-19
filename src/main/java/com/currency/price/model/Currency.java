package com.currency.price.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_SEQ")
    @SequenceGenerator(name = "CURRENCY_SEQ", sequenceName = "CURRENCY_SEQ", allocationSize = 1)
    private Integer id;

    private String currency;

    private Double buy;

    private Double sell;

    private String date;

    private String bank;

    public Currency(String currency, String buy, String sell) {
        this.currency = currency;
        try {
            this.buy = Double.parseDouble(buy);
            this.sell = Double.parseDouble(sell);
        } catch (NumberFormatException nfe) {
            this.buy = 0.0;
            this.sell = 0.0;
        }

    }

    public static Currency toCurrency(String string) {
        List<String> list = Arrays.stream(string.split(" "))
                .toList();
        try {
            return getCurrency(list.get(0), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(2)));
        } catch (NumberFormatException nfe) {
            return getCurrency(list.get(0), 0.0, 0.0);
        }
    }

    private static Currency getCurrency(String name, Double buy, Double sell) {
        return Currency.builder()
                .currency(name)
                .buy(buy)
                .sell(sell)
                .build();
    }
}
