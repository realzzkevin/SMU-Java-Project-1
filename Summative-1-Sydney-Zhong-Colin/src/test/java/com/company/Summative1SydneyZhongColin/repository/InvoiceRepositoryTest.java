package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.Game;
import com.company.Summative1SydneyZhongColin.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    private Invoice invoice;

    @Before
    public void setUp() throws Exception {
//        invoiceRepository.deleteAll();

        invoice = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                28,
                5);
        invoice.setUnitPrice(59.99);
        invoice.setTax(18.00);
        invoice.setProcessingFee(1.49);
        invoice.setSubtotal(299.95);
        invoice.setTotal(319.44);
        //invoice.setId(1);

    }

    @Test
    public void CreateAndFindInvoice () {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        Optional<Invoice> invoice1 = invoiceRepository.findById(savedInvoice.getId());
        assertEquals(savedInvoice, invoice1.get());

        invoiceRepository.deleteById(savedInvoice.getId());
        invoice1 = invoiceRepository.findById(savedInvoice.getId());

        assertFalse(invoice1.isPresent());

    }


    @Test
    public  void findAllInvoices () {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        List<Invoice> invoiceList = invoiceRepository.findAll();

        assertEquals(1, invoiceList.size());

    }

    @Test
    public void findByName() {
        Invoice savedInvice = invoiceRepository.save(invoice);
        List<Invoice> invoiceList = invoiceRepository.findByName(savedInvice.getName());
        assertEquals(1, invoiceList.size());
    }
}