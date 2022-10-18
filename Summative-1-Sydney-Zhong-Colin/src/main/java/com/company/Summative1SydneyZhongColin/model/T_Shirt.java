package com.company.Summative1SydneyZhongColin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_shirt")
public class T_Shirt {

    @Id
    @Column(name = "t_shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@NotNull
    private Integer id;

    @NotNull
    private String color;
    @NotNull
    private String size;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotNull
    private Integer quantity;

    public T_Shirt() {
    }

    public T_Shirt(Integer id, String color, String size, String description, double price, Integer quantity) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_Shirt t_shirt = (T_Shirt) o;
        return Double.compare(t_shirt.price, price) == 0 && Objects.equals(id, t_shirt.id) && Objects.equals(color, t_shirt.color) && Objects.equals(size, t_shirt.size) && Objects.equals(description, t_shirt.description) && Objects.equals(quantity, t_shirt.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, size, description, price, quantity);
    }

    @Override
    public String toString() {
        return "T_Shirt{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
