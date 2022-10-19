package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.Console;
import com.company.Summative1SydneyZhongColin.repository.ConsoleRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConsoleRepository consoleRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Console inputPS5;
    Console outputPS5;
    Console outputXbox;
    Console outputPS4;

    List<Console> allConsoles;
    List<Console> sonyConsoles;

    @Before
    public void setUp() throws Exception {
        inputPS5 = new Console("Playstation 5", "Sony", 499.99);
        outputPS5 = new Console("Playstation 5", "Sony", 499.99);
        outputPS5.setId(1);
        outputXbox = new Console("Xbox Series X|S", "Microsoft", 399.99);
        outputXbox.setId(2);
        outputPS4 = new Console("Playstation 4", "Sony", 199.99);
        outputPS4.setId(3);

        allConsoles = new ArrayList<>(Arrays.asList(outputPS5, outputXbox, outputPS4));
        sonyConsoles = new ArrayList<>(Arrays.asList(outputPS5, outputPS4));

        doReturn(outputPS5).when(consoleRepository).save(inputPS5);
        doReturn(Optional.of(outputPS5)).when(consoleRepository).findById(1);
        doReturn(allConsoles).when(consoleRepository).findAll();
        doReturn(sonyConsoles).when(consoleRepository).findAllConsolesByManufacturer("Sony");
    }

    @Test
    public void shouldCreateNewConsole() throws Exception {
        String inputJson = mapper.writeValueAsString(inputPS5);
        String outputJson = mapper.writeValueAsString(outputPS5);

        mockMvc.perform(post("/console")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsoleById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputPS5);

        mockMvc.perform(get("/console/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllConsoles() throws Exception {
        String outputJson = mapper.writeValueAsString(allConsoles);

        mockMvc.perform(get("/console"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingConsole() throws Exception {
        inputPS5.setId(1);
        inputPS5.setModel("PS5");

        String inputJson = mapper.writeValueAsString(inputPS5);

        mockMvc.perform(put("/console")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingConsole() throws Exception {
        int deleteConsoleID = 1;
        mockMvc.perform(delete("/console/1", deleteConsoleID))
                .andExpect(status().isNoContent());

        verify(consoleRepository).deleteById(deleteConsoleID);
    }

    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {
        String outputJson = mapper.writeValueAsString(sonyConsoles);

        mockMvc.perform(get("/console/manufacturer/Sony"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith422WhenTryingUpdateWithoutID() throws Exception {
        inputPS5.setModel("PS5");

        String inputJson = mapper.writeValueAsString(inputPS5);

        mockMvc.perform(put("/console")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldRespondWith422WhenCreateWithMissingProperties() throws Exception {
        inputPS5.setManufacturer(null);

        String inputJson = mapper.writeValueAsString(inputPS5);

        mockMvc.perform(post("/console")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldRespondWith422WhenUpdateWithMissingProperties() throws Exception {
        inputPS5.setModel(null);
        inputPS5.setId(4);

        String inputJson = mapper.writeValueAsString(inputPS5);

        mockMvc.perform(put("/console")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPriceIsTooLarge() throws Exception {
        inputPS5.setPrice(1000.00);

        String inputJson = mapper.writeValueAsString(inputPS5);

        mockMvc.perform(post("/console")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity());
    }
}