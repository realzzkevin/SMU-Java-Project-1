package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    private Game game;

    @Before
    public void setUp() throws Exception {
//        gameRepository.deleteAll();
        game =  new Game("Elden Ring",
                "FromSoftWare",
                "Mature",
                "The new fantasy action RPG",
                59.99,
                1000);
    }

    @Test
    public void addGetDeleteGame() {

        game = gameRepository.save(game);
        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game);

        gameRepository.deleteById(game.getId());

        game1 = gameRepository.findById(game.getId());

        assertFalse(game1.isPresent());
    }

    @Test
    public void updateGame() {

        game = gameRepository.save(game);

        game.setPrice(19.99);

        gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game);

    }

    @Test
    public void findByStudio() {
        game = gameRepository.save(game);
        List<Game> gameList = gameRepository.findByStudio(game.getStudio());
        assertEquals(gameList.get(0), game);
    }

    @Test
    public void findByESRBrating() {
        game = gameRepository.save(game);
        List<Game> gameList = gameRepository.findByESRBRating(game.getESRBRating());
//        System.out.println(gameList.get(0));
        assertEquals(gameList.get(0), game);
    }

    @Test
    public void findByTitle() {
        game = gameRepository.save(game);
        List<Game> gameList = gameRepository.findByTitle(game.getTitle());
        assertEquals(gameList.get(0), game);
    }
}