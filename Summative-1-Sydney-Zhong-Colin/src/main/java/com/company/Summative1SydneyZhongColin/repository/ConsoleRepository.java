package com.company.Summative1SydneyZhongColin.repository;


import com.company.Summative1SydneyZhongColin.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    List<Console> findAllConsolesByManufacturer(String manufacturer);

}
