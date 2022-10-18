package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.SalesTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTaxRepository extends JpaRepository <SalesTax, String>{
    SalesTax findByState(String state);
}
