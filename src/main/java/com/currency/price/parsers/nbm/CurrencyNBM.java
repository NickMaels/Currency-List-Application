package com.currency.price.parsers.nbm;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor  
public class CurrencyNBM {

    String numCode;

    String name;

    String charCode;

    Float value;

    @Override
    public String toString() {
        return charCode + "(" + name + ")" + ":  " + value + "\n";
    }
}

