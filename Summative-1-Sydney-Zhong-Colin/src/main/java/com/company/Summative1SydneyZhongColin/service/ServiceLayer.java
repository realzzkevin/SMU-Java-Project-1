package com.company.Summative1SydneyZhongColin.service;

import com.company.Summative1SydneyZhongColin.model.Console;
import com.company.Summative1SydneyZhongColin.model.Game;
import com.company.Summative1SydneyZhongColin.model.Invoice;
import com.company.Summative1SydneyZhongColin.model.T_Shirt;
import com.company.Summative1SydneyZhongColin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {

    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private T_ShirtRepository tShirtRepository;
    private InvoiceRepository invoiceRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private SaleTaxRepository saleTaxRepository;

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository, GameRepository gameRepository, T_ShirtRepository tShirtRepository, InvoiceRepository invoiceRepository, ProcessingFeeRepository processingFeeRepository, SaleTaxRepository saleTaxRepository) {
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.tShirtRepository = tShirtRepository;
        this.invoiceRepository = invoiceRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.saleTaxRepository = saleTaxRepository;
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        // check that invoice quantity is greater than zero
        if (invoice.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }

        invoice.setUnitPrice( getUnitPrice(invoice.getItemType(), invoice.getItemId()));

        Double subtotal = invoice.getUnitPrice() * invoice.getQuantity();
        Double tax = saleTaxRepository.findByState(invoice.getState()).getRate() * subtotal;
        System.out.println(tax);
        Double processingFee = processingFeeRepository.findByProductType(invoice.getItemType()).getFee();

        if (invoice.getQuantity() > 10) {
            processingFee += 15.49;
        }

        Double total = subtotal + processingFee + tax;

        // limit to 2 decimal points by rounding up
        tax = new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue();
        subtotal = new BigDecimal(subtotal).setScale(2, RoundingMode.HALF_UP).doubleValue();
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();

        invoice.setProcessingFee(processingFee);
        invoice.setTax(tax);
        invoice.setSubtotal(subtotal);
        invoice.setTotal(total);

        // throw error if total is over $999.99
        if (total > 999.99) {
            throw new IllegalArgumentException("Database cannot handle invoices over 999.99");
        }

        // decrement invoice quantity from item quantity
        switch (invoice.getItemType()) {
            case "Consoles":
                Optional<Console> console = consoleRepository.findById(invoice.getItemId());
                if (console.isPresent()) {
                    Console tempConsole = console.get();
                    if (tempConsole.getQuantity() < invoice.getQuantity()) {
                        throw new IllegalArgumentException("Invoice quantity is greater than remaining stock");
                    } else {
                        tempConsole.setQuantity(tempConsole.getQuantity() - invoice.getQuantity());
                        consoleRepository.save(tempConsole);
                    }
                } else {
                    throw new NotFoundException("console not found");
                }
                break;
            case "Games":
                Optional<Game> game = gameRepository.findById(invoice.getItemId());
                if (game.isPresent()) {
                    Game tempGame = game.get();
                    if (tempGame.getQuantity() < invoice.getQuantity()) {
                        throw new IllegalArgumentException("Invoice quantity is greater than remaining stock");
                    } else {
                        tempGame.setQuantity(tempGame.getQuantity() - invoice.getQuantity());
                        gameRepository.save(tempGame);
                    }
                } else {
                    throw new NotFoundException("game not found");
                }
                break;
            case "T-shirts":
                Optional<T_Shirt> tshirt = tShirtRepository.findById(invoice.getItemId());
                if (tshirt.isPresent()) {
                    T_Shirt tempTshirt = tshirt.get();
                    if (tempTshirt.getQuantity() < invoice.getQuantity()) {
                        throw new IllegalArgumentException("Invoice quantity is greater than remaining stock");
                    } else {
                        tempTshirt.setQuantity(tempTshirt.getQuantity() - invoice.getQuantity());
                        tShirtRepository.save(tempTshirt);
                    }
                } else {
                    throw new NotFoundException("t-shirt not found");
                }
                break;
            default:
                throw new NotFoundException("invalid item type");
        }

        return invoiceRepository.save(invoice);
    }

    public Double getUnitPrice (String itemType, int id) {
        switch (itemType) {
            case "Consoles":
                Optional<Console> console = consoleRepository.findById(id);
                if(console.isPresent()){
                    return console.get().getPrice();
                } else {
                    throw new NotFoundException("Console not found.");
                }
            case "Games":
                Optional<Game> game = gameRepository.findById(id);
                if (game.isPresent()) {
                    Game tempGame = game.get();
                    return game.get().getPrice();
                } else {
                    throw new NotFoundException("game not found");
                }
            case "T-shirts":
                Optional<T_Shirt> tshirt = tShirtRepository.findById(id);
                if (tshirt.isPresent()) {
                    return tshirt.get().getPrice();
                } else {
                    throw new NotFoundException("t-shirt not found");
                }
            default:
                throw new NotFoundException("invalid item type");
        }

    }

    public Invoice findInvoiceById(Integer id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> findInvoicesByCustomerName(String name) {
        return invoiceRepository.findByName(name);
    }
}
