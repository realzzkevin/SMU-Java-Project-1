package com.company.Summative1SydneyZhongColin.repository;


import com.company.Summative1SydneyZhongColin.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByStudio(String studio);
    List<Game> findByESRBRating(String esrb);
    List<Game> findByTitle(String title);
}
