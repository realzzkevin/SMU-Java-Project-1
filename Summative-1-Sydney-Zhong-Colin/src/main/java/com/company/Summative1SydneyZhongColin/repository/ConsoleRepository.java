package com.company.Summative1SydneyZhongColin.repository;


import com.company.Summative1SydneyZhongColin.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
}
