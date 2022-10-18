package com.company.Summative1SydneyZhongColin.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Game must have a title")
    private String title;
    @NotEmpty(message = "Please provide the studio for this game")
    private String studio;
    @NotEmpty(message = "Please provide the ESRB rating for this game")
    @Column(name = "esrb_rating")
    private String ESRBRating;
    @NotEmpty(message = "Please provide a short description ")
    private String description;
    @NotNull(message = "Please set a price for this game")
    @Digits(integer = 5, fraction = 2)
    private Double price;
    @NotNull(message = "please set the quantity for this game")
    private Integer quantity;

    public Game () {

    }

    public Game(String title, String studio, String ESRBRating, String description, Double price, Integer quantity) {
        this.title = title;
        this.studio = studio;
        this.ESRBRating = ESRBRating;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getESRBRating() {
        return ESRBRating;
    }

    public void setESRBRating(String ESRBRating) {
        this.ESRBRating = ESRBRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(studio, game.studio) && Objects.equals(ESRBRating, game.ESRBRating) && Objects.equals(description, game.description) && Objects.equals(price, game.price) && Objects.equals(quantity, game.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, studio, ESRBRating, description, price, quantity);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", studio='" + studio + '\'' +
                ", ESRBRating='" + ESRBRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
