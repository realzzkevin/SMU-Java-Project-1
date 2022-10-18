package com.company.Summative1SydneyZhongColin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t-shirt")
public class T_Shirt {

    @Id
    @Column(name = "t-shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String color;
    private String size;
    private String brand;

    public T_Shirt(int id, String color, String size, String brand) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_Shirt t_shirt = (T_Shirt) o;
        return id == t_shirt.id && Objects.equals(color, t_shirt.color) && Objects.equals(size, t_shirt.size) && Objects.equals(brand, t_shirt.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, size, brand);
    }

    @Override
    public String toString() {
        return "T_Shirt{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
