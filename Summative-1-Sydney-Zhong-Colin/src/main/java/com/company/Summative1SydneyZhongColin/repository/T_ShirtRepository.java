package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.T_Shirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_ShirtRepository extends JpaRepository<T_Shirt, Integer> {
    List<T_Shirt> findTShirtByColor(String color);

    List<T_Shirt> findTShirtBySize(String size);
}
