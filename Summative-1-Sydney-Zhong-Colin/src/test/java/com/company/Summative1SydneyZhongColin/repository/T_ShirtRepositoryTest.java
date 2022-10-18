package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.T_Shirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class T_ShirtRepositoryTest {

    @Autowired
    T_ShirtRepository t_shirtRepository;

    @Before
    public void setUp() throws Exception {
        t_shirtRepository.deleteAll(); //TODO:why is this needed?
    }

    @Test
    public void getAllTShirts() {
        T_Shirt t_shirt = new T_Shirt();
        t_shirt.setColor("Blue");
        t_shirt.setSize("Medium");
        t_shirt.setDescription("A medium blue t-shirt.");
        t_shirt.setPrice(20.00);
        t_shirt.setQuantity(10);

        t_shirt = t_shirtRepository.save(t_shirt);

        List<T_Shirt> t_shirtList = t_shirtRepository.findAll();
        assertEquals(t_shirtList.size(), 1);
    }

}