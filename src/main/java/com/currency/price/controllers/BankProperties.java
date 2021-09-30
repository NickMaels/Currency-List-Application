package com.currency.price.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties("bank")
public class BankProperties {

    private Map<String, BankProperty> properties = new HashMap<>();

    public BankProperty getProperty(String bank) {
        return properties.get(bank);
    }

    @Getter
    @Setter
    public static class BankProperty{
        private String title;

        private String link;

        private String tag;
    }
}
