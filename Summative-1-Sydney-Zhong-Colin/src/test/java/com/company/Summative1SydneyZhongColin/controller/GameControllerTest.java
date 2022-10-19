package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.Game;
import com.company.Summative1SydneyZhongColin.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Game eldenRing;
    private String eldenJson;
    private List<Game> allGames = new ArrayList<>();
    private String allGamesJson;

    @Before
    public void setUp() throws Exception {
        eldenRing =  new Game("Elden Ring",
                "FromSoftWare",
                "Mature",
                "The new fantasy action RPG",
                59.99,
                1000);

        eldenJson = mapper.writeValueAsString(eldenRing);

        Game ReVillage = new Game("Resident Evil Village",
                "CAPCOM",
                "Mature",
                "Experience Suvival horror like never before",
                39.99,
                500);
        allGames.add(eldenRing);
        allGames.add(ReVillage);

        allGamesJson = mapper.writeValueAsString(allGames);
    }

    @Test
    public void shouldCreateNewGameOnPostRequest() throws Exception {
        Game inputGame = new Game("Elden Ring",
                "FromSoftWare",
                "Mature",
                "The new fantasy action RPG",
                59.99,
                1000);

        String inputGameJson = mapper.writeValueAsString(inputGame);

        doReturn(eldenRing).when(repo).save(inputGame);

        mockMvc.perform(
                post("/game")
                        .content(inputGameJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().json(eldenJson));
    }

    @Test
    public void shouldReturnGameById() throws Exception{
        doReturn(Optional.of(eldenRing)).when(repo).findById(anyInt());

       mockMvc.perform(
                get("/game/1")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(eldenJson));
    }
    @Test
    public void shouldReturnAllGames() throws Exception{
        doReturn(allGames).when(repo).findAll();

        mockMvc.perform(
                get("/game")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allGamesJson));
    }

    @Test
    public void shouldReturnOKForNonExistentRsvpId() throws Exception {
        doReturn(Optional.empty()).when(repo).findById(12345);

        mockMvc.perform(
                get("/game/12345")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn204StatusCodeUpdateGame() throws Exception{
        Game savedEldenRing = eldenRing;
        savedEldenRing.setId(12);
        String savedJson = mapper.writeValueAsString(savedEldenRing);
        mockMvc.perform(
                put("/game")
                        .content(savedJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn204StatusCodeByDeleteGame() throws Exception {
        mockMvc.perform(
                delete("/game/1")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnGameByStudio() throws Exception {
        List<Game> gameByStudio = new ArrayList<>();
        gameByStudio.add(eldenRing);
        String gameByStudioJson = mapper.writeValueAsString(gameByStudio);

        doReturn(gameByStudio).when(repo).findByStudio("fromsoftware");

        mockMvc.perform(
                get("/game/studio/fromsoftware")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gameByStudioJson));
    }

    @Test
    public void getGamesByEsrb() throws Exception {
        List<Game> gamesByEsrb = new ArrayList<>();
        gamesByEsrb.add(eldenRing);
        String gamesByEsrbJson = mapper.writeValueAsString(gamesByEsrb);

        doReturn(gamesByEsrb).when(repo).findByESRBRating("Mature");

        mockMvc.perform(
                        get("/game/esrb/Mature")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gamesByEsrbJson));
    }

    @Test
    public void getGamesByTitle() throws Exception {
        List<Game> gamesByTitle = new ArrayList<>();
        gamesByTitle.add(eldenRing);
        String gamesByTitleJson = mapper.writeValueAsString(gamesByTitle);
        doReturn(gamesByTitle).when(repo).findByTitle("EldenRing");

        mockMvc.perform(
                        get("/game/title/EldenRing")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gamesByTitleJson));
    }

    //UpdateGame will return 422 error if RequestBody doesn't have id
    @Test
    public void shouldReturn422StatusCodeByUpdateGameWithoutID() throws Exception{
        Game inputGameNoId =  new Game("Elden Ring",
                "FromSoftWare",
                "Mature",
                "The new fantasy action RPG",
                59.99,
                1000);

        String inputJson = mapper.writeValueAsString(inputGameNoId);

        mockMvc.perform(
                put("/game")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    //Controller will return 422 code, if required properties are missing
    @Test
    public void shouldReturn422StatusCreateOrUpdateGameWithMissingProperties() throws Exception {
        Game inputGame = eldenRing;
        inputGame.setTitle(null);

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                post("/game")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inputGame.setId(20);

        inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        put("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void shouldReturn422statusWhenPropertiesExceedLimitedLength() throws Exception {
        Game inputGame = eldenRing;
        inputGame.setStudio("FromSoftWareFromSoftWareFromSoftWareFromSoftWareFromSoftWare");

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                post("/game")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusWhenPropertiesExceedLimit() throws Exception {
        Game inputGame = eldenRing;
        inputGame.setPrice(1000.00);

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        post("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        inputGame.setId(20);

        inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        put("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusWhenPropertiesAreNotNumber() throws Exception {
        String inputJson = "{title\":\"dark souls 3\",\"studio\":\"FromSoftWare\",\"description\":\"The new fantasy action RPG\",\"price\":\"this is a string\",\"quantity\":\"this is a string too\",\"esrbrating\":\"Mature\"}";

        mockMvc.perform(
                        post("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        String inputJson2 = "{\"id\":15,title\":\"dark souls 3\",\"studio\":\"FromSoftWare\",\"description\":\"The new fantasy action RPG\",\"price\":\"this is a string\",\"quantity\":\"this is a string too\",\"esrbrating\":\"Mature\"}";

        mockMvc.perform(
                        put("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

}