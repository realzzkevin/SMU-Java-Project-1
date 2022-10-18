package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.ProcessingFee;
import com.company.Summative1SydneyZhongColin.model.SalesTax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessingFeeRepositoryTest {

    @Autowired
    ProcessingFeeRepository processingFeeRepository;

    @Before
    public void setUp() throws Exception {
       ProcessingFee fee1 = new ProcessingFee();
       fee1.setProductType("Consoles");
       fee1.setFee(14.99);
       ProcessingFee fee2 = new ProcessingFee();
       fee2.setProductType("T-shirts");
       fee2.setFee(1.98);
       ProcessingFee fee3 = new ProcessingFee();
       fee3.setProductType("Games");
       fee3.setFee(1.49);

       processingFeeRepository.save(fee1);
       processingFeeRepository.save(fee2);
       processingFeeRepository.save(fee3);
    }

    @Test
    public void findByProductType() {
        ProcessingFee fee = processingFeeRepository.findByProductType("T-shirts");

        assertEquals(0.0001, 1.49, fee.getFee() );
        System.out.println(fee.toString());
    }
}