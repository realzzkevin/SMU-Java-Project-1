package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.T_Shirt;
import com.company.Summative1SydneyZhongColin.repository.T_ShirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(T_ShirtController.class)
public class T_ShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    T_ShirtRepository t_shirtRepository;
    T_Shirt inputTshirt1;
    T_Shirt outputTshirt1;

    T_Shirt inputTshirt2;
    T_Shirt outputTshirt2;

    T_Shirt inputTshirt3;
    T_Shirt outputTshirt3;

    List<T_Shirt> tshirtList;
    List<T_Shirt> tshirtColorList;
    List<T_Shirt> tshirtSizeList;


    @Before
    public void setUp() {

        inputTshirt1 = new T_Shirt(4,"Blue", "Medium", "a blue t-shirt", 20.00, 10);
        inputTshirt2 = new T_Shirt(5,"Red", "Large", "a red t-shirt", 20.00, 10);
        inputTshirt3 = new T_Shirt(6,"Green", "Small", "a green t-shirt", 20.00, 10);

        outputTshirt1 = new T_Shirt(4,"Blue", "Medium", "a blue t-shirt", 20.00, 10);
        outputTshirt2 = new T_Shirt(5,"Red", "Large", "a red t-shirt", 20.00, 10);
        outputTshirt3 = new T_Shirt(6,"Green", "Small", "a green t-shirt", 20.00, 10);


        tshirtList = new ArrayList<>(Arrays.asList(
                outputTshirt1, outputTshirt2, outputTshirt3
        ));

        tshirtColorList = new ArrayList<>(Arrays.asList(
                outputTshirt1
        ));

        tshirtSizeList = new ArrayList<>(Arrays.asList(
                outputTshirt3
        ));

        doReturn(outputTshirt1).when(t_shirtRepository).save(inputTshirt1);
        doReturn(outputTshirt2).when(t_shirtRepository).save(inputTshirt2);
        doReturn(outputTshirt3).when(t_shirtRepository).save(inputTshirt3);

        doReturn(Optional.of(outputTshirt1)).when(t_shirtRepository).findById(4);
        doReturn(Optional.of(outputTshirt2)).when(t_shirtRepository).findById(5);
        doReturn(Optional.of(outputTshirt3)).when(t_shirtRepository).findById(6);

        doReturn(tshirtColorList).when(t_shirtRepository).findTShirtByColor("Blue");
        doReturn(tshirtColorList).when(t_shirtRepository).findTShirtByColor("Red");
        doReturn(tshirtColorList).when(t_shirtRepository).findTShirtByColor("Green");

        doReturn(tshirtSizeList).when(t_shirtRepository).findTShirtBySize("Small");
        doReturn(tshirtSizeList).when(t_shirtRepository).findTShirtBySize("Medium");
        doReturn(tshirtSizeList).when(t_shirtRepository).findTShirtBySize("Large");

        doReturn(tshirtList).when(t_shirtRepository).findAll();

    }

    @Test
    public void shouldCreateTShirt() throws Exception {
        String createJson = mapper.writeValueAsString(inputTshirt1);
        String outputJson = mapper.writeValueAsString(outputTshirt1);

        mockMvc.perform(post("/tshirt")
                .content(createJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTShirtById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTshirt2);
        mockMvc.perform(get("/tshirt/5"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllTShirts() throws Exception {
        String outputJson = mapper.writeValueAsString(tshirtList);
        mockMvc.perform(get("/tshirt"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateATShirtById() throws Exception {
        inputTshirt2.setPrice(25.00);
        inputTshirt2.setId(7);
        String createJson = mapper.writeValueAsString(inputTshirt2);

        mockMvc.perform(put("/tshirt")
                .content(createJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteById() throws Exception {
        mockMvc.perform(delete("/tshirt/6"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllTShirtsByColor() throws Exception {
        String outputJson = mapper.writeValueAsString(tshirtColorList);
        mockMvc.perform(get("/tshirt/color/Red"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllTShirtsBySize() throws Exception {
        String outputJson = mapper.writeValueAsString(tshirtSizeList);
        mockMvc.perform(get("/tshirt/size/Medium"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

}