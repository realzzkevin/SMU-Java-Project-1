//package com.company.Summative1SydneyZhongColin.seeder;
//
//import com.company.Summative1SydneyZhongColin.model.SalesTax;
//import com.company.Summative1SydneyZhongColin.repository.SaleTaxRepository;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Seeder {
//    @Autowired
//    SaleTaxRepository taxRepository;
//
//    List<SalesTax> taxList = new ArrayList<>(Arrays.asList(
//            new SalesTax("AL", .05),
//            new SalesTax("AK", .06),
//            new SalesTax("AZ", .04),
//            new SalesTax("AR", .06),
//            new SalesTax("CA", .06),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05),
//            new SalesTax("AL", .05)
//
//
//    ));
//
//    public void setTaxes () {
//        taxRepository.saveAll(taxList);
//    }
//
//    public static void main(String[] args) {
//        Seeder seeder = new Seeder();
//        seeder.setTaxes();
//    }
//}
