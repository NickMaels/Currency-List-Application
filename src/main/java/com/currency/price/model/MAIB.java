package com.currency.price.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "currency")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MAIB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String currency;

    private Double buy;

    private Double sell;

    private String date;

    private String bank;

    @Override
    public String toString() {
        return currency + "\n";
    }
}
