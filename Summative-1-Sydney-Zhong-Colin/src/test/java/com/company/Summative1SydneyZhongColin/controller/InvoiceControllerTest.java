package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.Invoice;
import com.company.Summative1SydneyZhongColin.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer serviceLayer;

    private String invoice1Json;

    private String invoice1OutputJson;

    private Invoice invoice1;

    private Invoice invoice1Out;

    @Before
    public void setUp() throws Exception {

        invoice1 = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                18,
                5);

        invoice1Json = mapper.writeValueAsString(invoice1);

        invoice1Out =  new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                18,
                5);
        invoice1Out.setId(3);
        invoice1Out.setUnitPrice(59.99);
        invoice1Out.setTax(0.06);
        invoice1Out.setProcessingFee(1.49);
        invoice1Out.setSubtotal(179.97);
        invoice1Out.setTotal(192.26);

        invoice1OutputJson = mapper.writeValueAsString(invoice1Out);

    }

    @Test
    public void shouldCreateNewInvoice() throws Exception {
        doReturn(invoice1Out).when(serviceLayer).saveInvoice(invoice1);

        mockMvc.perform(
                post("/invoice")
                        .content(invoice1Json)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(invoice1OutputJson));
    }

    @Test
    public void shouldReturnInvoiceById() throws Exception {
        doReturn(invoice1Out).when(serviceLayer).findInvoiceById(3);

        mockMvc.perform(
                get("/invoice/3")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(invoice1OutputJson));
    }

    @Test
    public void shouldReturnOkForNonExistentInvoiceId() throws Exception {
        doReturn(null).when(serviceLayer).findInvoiceById(123);

        mockMvc.perform(
                        get("/invoice/123")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllInvoices() throws Exception {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice1Out);

        Invoice invoice2Out =  new Invoice(
                "KevinZ",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                18,
                15);
        invoice2Out.setId(5);
        invoice2Out.setTax(0.06);
        invoice2Out.setProcessingFee(1.49);
        invoice2Out.setSubtotal(899.85);
        invoice2Out.setTotal(970.82);
        invoiceList.add(invoice2Out);

        Invoice invoice3Out =  new Invoice(
                "KZ",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                18,
                10);
        invoice3Out.setId(6);
        invoice3Out.setTax(0.06);
        invoice3Out.setProcessingFee(1.49);
        invoice3Out.setSubtotal(599.9);
        invoice3Out.setTotal(637.38);
        invoiceList.add(invoice3Out);

        String invoiceListJson = mapper.writeValueAsString(invoiceList);

        doReturn(invoiceList).when(serviceLayer).findAllInvoices();

        mockMvc.perform(
                get("/invoice")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(invoiceListJson));

    }

    @Test
    public void shouldReturnInvoicesByCustomerName() throws Exception {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice1Out);
        String invoiceListJson = mapper.writeValueAsString(invoiceList);
        doReturn(invoiceList).when(serviceLayer).findInvoicesByCustomerName("Kevin");

        mockMvc.perform(
                get("/invoice/customer/Kevin")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(invoiceListJson));
    }

    @Test
    public void shouldReturn422StatusCreateInvoiceWithoutRequiredProperties() throws Exception {
        Invoice inputInvoice =  new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                18,
                5);
        inputInvoice.setItemType(null);
        String inputInvoicJson = mapper.writeValueAsString(inputInvoice);
        mockMvc.perform(
                post("/invoice")
                        .content(inputInvoicJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


    }

    @Test
    public void shouldReturn422StatusWhenItemIdOrQuantityAreNotNumbers() throws Exception {
        String inputInvoiceJson =
                "    {\n" +
                        "        \"name\": \"kevinZ\",\n" +
                        "        \"street\": \"1000 main St\",\n" +
                        "        \"city\": \"Philadelphia\",\n" +
                        "        \"state\": \"PA\",\n" +
                        "        \"zipcode\": \"19102\",\n" +
                        "        \"itemType\": \"Games\",\n" +
                        "        \"itemId\" : \"item id is a string\",\n" +
                        "        \"quantity\": 1\n" +
                        "\n" +
                        "    }";

        mockMvc.perform(
                post("/invoice")
                        .contentType(inputInvoiceJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        String inputInvoiceJson2 =
                "    {\n" +
                "        \"name\": \"kevinZ\",\n" +
                "        \"street\": \"1000 main St\",\n" +
                "        \"city\": \"Philadelphia\",\n" +
                "        \"state\": \"PA\",\n" +
                "        \"zipcode\": \"19102\",\n" +
                "        \"itemType\": \"Games\",\n" +
                "        \"itemId\" : 15,\n" +
                "        \"quantity\": \"quantity is a string\"\n" +
                "\n" +
                "    }";
        mockMvc.perform(
                        post("/invoice")
                                .contentType(inputInvoiceJson2)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}