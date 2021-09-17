package com.currency.price.repositories;

import com.currency.price.model.MAIB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MAIBRepository extends JpaRepository<MAIB, Integer> {

    List<MAIB> findAllByDate(String date);

    List<MAIB> findAllByBank(String bank);

    @Query(value = "SELECT * FROM CURRENCY WHERE BANK LIKE '%MAIB%' AND DATE =:date", nativeQuery = true)
    List<MAIB> findByBankAndDate(@Param("date") String date);
}
