package com.company.Summative1SydneyZhongColin.service;

import com.company.Summative1SydneyZhongColin.model.*;
import com.company.Summative1SydneyZhongColin.repository.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

//@RunWith(SpringRunner.class)
public class ServiceLayerTest {

    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private T_ShirtRepository tShirtRepository;
    private InvoiceRepository invoiceRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private SaleTaxRepository saleTaxRepository;

    private ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();
        setUpGameRepository();
        setUptShirtRepository();
        setUpInvoiceRepository();
        setUpProcessingFeeRepository();
        setUpSaleTaxRepository();

        serviceLayer = new ServiceLayer(
                consoleRepository,
                gameRepository,
                tShirtRepository,
                invoiceRepository,
                processingFeeRepository,
                saleTaxRepository);
    }
    @Test
    public void doSomething() {
        System.out.println("I still don't know why it here");

    }
    public void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        Console console1 = new Console("Switch", "Nintendo", 299.99);
        Console console1saved = new Console("Switch", "Nintendo", 299.99);
        console1saved.setId(1);
        console1saved.setMemoryAmount("8G");
        console1saved.setProcessor("AMD");
        console1saved.setQuantity(100);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1saved);
       doReturn(console1saved).when(consoleRepository).save(console1);
       doReturn(Optional.of(console1saved)).when(consoleRepository).findById(1);
       doReturn(consoleList).when(consoleRepository).findAll();

        //set a game
    }
    public void setUpGameRepository() {
        gameRepository = mock(GameRepository.class);

        Game eldenRing =  new Game("Elden Ring",
                "FromSoftWare",
                "Mature",
                "The new fantasy action RPG",
                59.99,
                1000);

//       String eldenJson = mapper.writeValueAsString(eldenRing);

        Game eldenRingSaved = new Game("Elden Ring",
                "FromSoftWare",
                "Mature",
                "The new fantasy action RPG",
                59.99,
                1000);

        eldenRingSaved.setId(1);

        Game ReVillage = new Game("Resident Evil Village",
                "CAPCOM",
                "Mature",
                "Experience Suvival horror like never before",
                39.99,
                500);

        List<Game> gameList = new ArrayList<>();
        gameList.add(eldenRingSaved);

        doReturn(eldenRingSaved).when(gameRepository).save(eldenRing);
        doReturn(Optional.of(eldenRingSaved)).when(gameRepository).findById(1);
        doReturn(gameList).when(gameRepository).findAll();

    }
    public void setUptShirtRepository () {
        tShirtRepository = mock(T_ShirtRepository.class);
        T_Shirt t_shirt1 = new T_Shirt("Red", "M", "It's a red shirt, stay safe.", 5.99, 10000);

        T_Shirt t_shirt1Saved = new T_Shirt("Red", "M", "It's a red shirt, stay safe.", 5.99, 10000);

        t_shirt1Saved.setId(1);
        doReturn(t_shirt1Saved).when(tShirtRepository).save(t_shirt1);
        doReturn(Optional.of(t_shirt1Saved)).when(tShirtRepository).findById(1);
    }
    public void setUpInvoiceRepository () {
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice1 = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                1,
                5);
        invoice1.setUnitPrice(59.99);
        invoice1.setSubtotal(299.95);
        invoice1.setTax(18.00);
        invoice1.setProcessingFee(1.49);
        invoice1.setTotal(319.44);

        Invoice outputInvoice = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                1,
                5);
        outputInvoice.setUnitPrice(59.99);
        outputInvoice.setSubtotal(299.95);
        outputInvoice.setTax(18.00);
        outputInvoice.setProcessingFee(1.49);
        outputInvoice.setTotal(319.44);

        outputInvoice.setId(1);

        doReturn(outputInvoice).when(invoiceRepository).save(invoice1);
        doReturn(Optional.of(outputInvoice)).when(invoiceRepository).findById(1);


    }
    public void setUpProcessingFeeRepository() {
        processingFeeRepository = mock(ProcessingFeeRepository.class);
        ProcessingFee gameFee = new ProcessingFee();
        ProcessingFee consoleFee = new ProcessingFee();
        ProcessingFee tshirtFee = new ProcessingFee();

        gameFee.setProductType("Games");
        gameFee.setFee(1.49);


        doReturn(gameFee).when(processingFeeRepository).findByProductType("Games");
    }

    public void setUpSaleTaxRepository() {
        saleTaxRepository = mock(SaleTaxRepository.class);
        SalesTax salesTax = new SalesTax("PA", 0.06);

        doReturn(salesTax).when(saleTaxRepository).findByState("PA");
    }


    @Test
    public void shouldSaveInvoice() {

        Invoice invoice1 = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                1,
                5);

        Invoice outputInvoice = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                1,
                5);
        outputInvoice.setUnitPrice(59.99);
        outputInvoice.setTax(18.00);
        outputInvoice.setProcessingFee(1.49);
        outputInvoice.setSubtotal(299.95);
        outputInvoice.setTotal(319.44);

        outputInvoice.setId(1);

       Invoice savedInvoice = serviceLayer.saveInvoice(invoice1);
        System.out.println(savedInvoice);

       assertEquals(outputInvoice, savedInvoice );


    }

    @Test
    public void shouldFindInvoiceById() {
        Invoice expectedOutput = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                1,
                5);
        expectedOutput.setUnitPrice(59.99);
        expectedOutput.setTax(18.00);
        expectedOutput.setProcessingFee(1.49);
        expectedOutput.setSubtotal(299.95);
        expectedOutput.setTotal(319.44);
        expectedOutput.setId(1);

        Invoice actualOutput = serviceLayer.findInvoiceById(1);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldFindAllInvoices() {
    }

    @Test
    public void shouldFindInvoicesByCustomerName() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturn422IfItemIdNoFound() {
        Invoice inputInvoice = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                123,
                5);
        System.out.println(inputInvoice);

        Invoice actualOutput = serviceLayer.saveInvoice(inputInvoice);
        System.out.println("No Exceptions, Unexpected. ");
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturn422IfItemIdIsMissing() {
        Invoice inputInvoice = new Invoice(
                "Kevin",
                "1000 Main st",
                "Philadelphia",
                "PA",
                "19102",
                "Games",
                123,
                5);
        inputInvoice.setItemId(null);
        Invoice actualOutput = serviceLayer.saveInvoice(inputInvoice);
        System.out.println("No Exception, Unexpected. ");
    }
}