package com.company.Summative1SydneyZhongColin.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String studio;
    @Column(name = "esrbrating")
    private String ESRBRating;
    private String title;

    public Game(int id, String studio, String ESRBRating, String title) {
        this.id = id;
        this.studio = studio;
        this.ESRBRating = ESRBRating;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && Objects.equals(studio, game.studio) && Objects.equals(ESRBRating, game.ESRBRating) && Objects.equals(title, game.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studio, ESRBRating, title);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", studio='" + studio + '\'' +
                ", ESRBRating='" + ESRBRating + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
