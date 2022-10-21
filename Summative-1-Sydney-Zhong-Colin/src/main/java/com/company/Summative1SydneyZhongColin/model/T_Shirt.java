package com.company.Summative1SydneyZhongColin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_shirt")
public class T_Shirt {

    @Id
    @Column(name = "t_shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20, message = "you cannot have a color name that has more than 20 characters.")
    @NotEmpty(message = "color must not be empty")
    private String color;
    @Size(max = 20, message = "you cannot have a size that has more than 20 characters.")
    @NotEmpty(message = "size must not be empty")
    private String size;
    @Size(max = 255, message= "a description for a t-shirt cannot be more than 255 characters.")
    @NotEmpty(message = "description must not be empty")
    private String description;
    @NotNull(message = "price must be set")
    @Digits(integer = 3, fraction = 2, message = "price cannot be more than $999.99.")
    private double price;
    @NotNull(message = "quantity must be set")
    private Integer quantity;

    public T_Shirt() {
    }

    public T_Shirt(String color, String size, String description, double price, Integer quantity) {
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
