package com.company.Summative1SydneyZhongColin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.MessageInterpolator;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer id;

    @NotEmpty( message = "Name cannot be empty.")
    @Size(max = 80, message = "Name must under 80 characters.")
    private String name;

    @NotEmpty(message = "Street cannot be empty.")
    @Size(max = 30, message = "Name must under 30 characters.")
    private String street;

    @NotEmpty(message = "City cannot be empty")
    @Size(max = 30, message = "City must under 30 characters")
    private String city;

    @Size(max = 2, min = 2, message = "state should be two letters")
    @NotEmpty(message = "State can not be empty.")
    private String state;

    @Size(max = 5, min = 5, message = "Zipcode has 5 digits")
    @NotEmpty(message = "Zipcode cannot be empty ")
    private String zipcode;

    @NotEmpty(message = "Item type cannot be empty.")
    @Column(name = "item_type")
    @Size(max = 20, message = "Item Type must under 20 characters")
    private String itemType;

    @NotNull(message = "Item id Cannot be empty")
    @Column(name = "item_id")
    private Integer itemId;

    @NotNull(message = "Unit price cannot be empty")
    @Column(name = "unit_price")
    @Digits(integer = 3, fraction = 2, message = "unit price cannot be more than 999.99")
    private Double unitPrice;

    @NotNull(message = "Quantity number cannot be empty")
    private Integer quantity;

//    @NotNull(message = "Subtotal cannot be empty")
    @Digits(integer = 3, fraction = 2, message = "Subtotal cannot be more than 999.99")
    private Double subtotal;

//    @NotNull(message = "Tax cannot be empty.")
    @Digits(integer = 3, fraction = 2, message = "Tax cannot be more than 999.99")
    private Double tax;

//    @NotNull(message = "Processing fee cannot be empty.")
    @Column(name = "processing_fee")
    @Digits(integer = 3, fraction = 2, message = "Processing fee cannot be more than 999.99")
    private Double processingFee;

//    @NotNull(message = "Total number cannot be empty")
    @Digits(integer = 3, fraction = 2, message = "Total cannot be more than 999.99")
    private Double total;

    public Invoice () {}

    public Invoice(String name,
                   String street,
                   String city,
                   String state,
                   String zipcode,
                   String itemType,
                   Integer itemId,
                   Double unitPrice,
                   Integer quantity) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(itemId, invoice.itemId) && Objects.equals(unitPrice, invoice.unitPrice) && Objects.equals(quantity, invoice.quantity) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
