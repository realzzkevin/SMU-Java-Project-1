package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.Invoice;
import com.company.Summative1SydneyZhongColin.repository.InvoiceRepository;
import com.company.Summative1SydneyZhongColin.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    private ServiceLayer serviceLayer;

    // create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    // get
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Integer id) {
        return serviceLayer.findInvoiceById(id);
    }

    // get all
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    // get by customer name
    @GetMapping("/customer/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomerName(@PathVariable String name) {
        return serviceLayer.findInvoicesByCustomerName(name);
    }
}
