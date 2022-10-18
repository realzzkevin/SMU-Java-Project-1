package com.company.Summative1SydneyZhongColin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "processing_fee")
public class ProcessingFee {

    @NotEmpty(message = "Product type must not be null or empty")
    @Column(name = "product_type")
    private String productType;
    @Digits(integer = 4, fraction = 2)
    private Double fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return Objects.equals(getProductType(), that.getProductType()) && Objects.equals(getFee(), that.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }

    @Override
    public String toString() {
        return "ProcessingFee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
