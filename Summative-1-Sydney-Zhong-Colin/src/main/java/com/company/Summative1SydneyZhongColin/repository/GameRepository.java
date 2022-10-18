package com.company.Summative1SydneyZhongColin.repository;


import com.company.Summative1SydneyZhongColin.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
