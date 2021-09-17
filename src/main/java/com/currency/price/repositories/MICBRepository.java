package com.currency.price.repositories;

import com.currency.price.model.MICB;
import com.currency.price.parsers.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MICBRepository extends JpaRepository<MICB, Integer> {

    List<MICB> findAllByDate(String date);

    List<MICB> findAllByBank(String bank);

    @Query(value = "SELECT * FROM CURRENCY WHERE BANK LIKE '%MICB%' AND DATE =:date", nativeQuery = true)
    List<MICB> findByBankAndDate(@Param("date") String date);
}
