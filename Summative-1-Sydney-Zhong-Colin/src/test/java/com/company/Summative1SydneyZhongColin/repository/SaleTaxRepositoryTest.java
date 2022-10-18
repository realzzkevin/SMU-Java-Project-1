package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.SalesTax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleTaxRepositoryTest {

    @Autowired
    SaleTaxRepository saleTaxRepository;

    private SalesTax tax;

    @Before
    public void setUp() throws Exception {

        List<SalesTax> taxes = new ArrayList<>(Arrays.asList(
                new SalesTax("AL", .05),
                new SalesTax("AK", .06),
                new SalesTax("AZ", .04),
                new SalesTax("AR", .06),
                new SalesTax("CA", .06),
                new SalesTax("CO", .04),
                new SalesTax("CT", .03),
                new SalesTax("DE", .05),
                new SalesTax("FL", .06),
                new SalesTax("GA", .07),
                new SalesTax("HI", .05),
                new SalesTax("ID", .03),
                new SalesTax("IL", .05),
                new SalesTax("IN", .05),
                new SalesTax("IA", .04),
                new SalesTax("KS", .06),
                new SalesTax("KY", .04),
                new SalesTax("LA", .05),
                new SalesTax("ME", .03),
                new SalesTax("MD", .07),
                new SalesTax("MA", .05),
                new SalesTax("MI", .06),
                new SalesTax("MN", .06),
                new SalesTax("MS", .05),
                new SalesTax("MO", .05),
                new SalesTax("MT", .03),
                new SalesTax("NE", .04),
                new SalesTax("NV", .04),
                new SalesTax("NH", .06),
                new SalesTax("NJ", .05),
                new SalesTax("NM", .05),
                new SalesTax("NY", .06),
                new SalesTax("NC", .05),
                new SalesTax("ND", .05),
                new SalesTax("OH", .04),
                new SalesTax("OK", .04),
                new SalesTax("OR", .07),
                new SalesTax("PA", .06),
                new SalesTax("RI", .06),
                new SalesTax("SC", .06),
                new SalesTax("SD", .06),
                new SalesTax("TN", .05),
                new SalesTax("TX", .03),
                new SalesTax("UT", .04),
                new SalesTax("VT", .07),
                new SalesTax("VA", .06),
                new SalesTax("WA", .05),
                new SalesTax("WV", .05),
                new SalesTax("WI", .03),
                new SalesTax("WY", .04)
                ));
        saleTaxRepository.saveAll(taxes);
        System.out.println(taxes.size());
    }

    @Test
    public void findByState() throws Exception {
        tax = saleTaxRepository.findByState("AK");
        assertEquals(0.001, 0.06, tax.getRate());
        List allState = saleTaxRepository.findAll();
        assertEquals(50, allState.size());

        tax = saleTaxRepository.findByState("AL");
        assertEquals(0.001,0.05, tax.getRate());
    }
}