package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.T_Shirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class T_ShirtRepositoryTest {

    @Autowired
    T_ShirtRepository t_shirtRepository;

    private T_Shirt t_shirt;

    @Before
    public void setUp() throws Exception {
//        t_shirtRepository.deleteAll();
        t_shirt = new T_Shirt();
        t_shirt.setColor("Blue");
        t_shirt.setSize("Medium");
        t_shirt.setDescription("A medium blue t-shirt.");
        t_shirt.setPrice(20.00);
        t_shirt.setQuantity(10);
    }

    @Test
    public void addGetDeleteTShirt() {
        t_shirt = t_shirtRepository.save(t_shirt);
        Optional<T_Shirt> t_shirt1 = t_shirtRepository.findById(t_shirt.getId());
        assertEquals(t_shirt1.get(), t_shirt);

        t_shirtRepository.deleteById(t_shirt.getId());
        t_shirt1 = t_shirtRepository.findById(t_shirt.getId());
        assertFalse(t_shirt1.isPresent());
    }

    @Test
    public void getAllTShirts() {

        t_shirt = t_shirtRepository.save(t_shirt);

        List<T_Shirt> t_shirtList = t_shirtRepository.findAll();
        assertEquals(t_shirtList.size(), 1);
    }

    @Test
    public void updateTShirt() {

        t_shirt = t_shirtRepository.save(t_shirt);
        t_shirt.setPrice(40.00);
        t_shirtRepository.save(t_shirt);

        Optional<T_Shirt> tshirt1 = t_shirtRepository.findById(t_shirt.getId());
        assertEquals(tshirt1.get(), t_shirt);

    }

    @Test
    public void findTShirtByColor() {

        t_shirt = t_shirtRepository.save(t_shirt);
        List<T_Shirt> t_shirtList = t_shirtRepository.findTShirtByColor(t_shirt.getColor());
        assertEquals(t_shirtList.get(0), t_shirt);
    }

    @Test
    public void findTShirtBySize() {

        t_shirt = t_shirtRepository.save(t_shirt);
        List<T_Shirt> t_shirtList = t_shirtRepository.findTShirtBySize(t_shirt.getSize());
        assertEquals(t_shirtList.get(0), t_shirt);
    }



}