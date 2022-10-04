package com.currency.price.repositories;

import com.currency.price.parsers.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    @Query(value = "SELECT * FROM CURRENCY WHERE BANK = :name AND DATE =:date", nativeQuery = true)
    List<Currency> findByBankAndDate(@Param("date") String date, @Param("name") String name);

    @Query(value = "SELECT DISTINCT DATE FROM CURRENCY WHERE BANK = :name", nativeQuery = true)
    List<String> findAllByBank(@Param("name") String name);
}
