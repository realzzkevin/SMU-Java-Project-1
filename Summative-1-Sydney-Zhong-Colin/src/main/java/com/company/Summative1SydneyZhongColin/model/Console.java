package com.company.Summative1SydneyZhongColin.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console {

    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String manufacturer;

    public Console(int id, String manufacturer) {
        this.id = id;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return id == console.id && Objects.equals(manufacturer, console.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer);
    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
