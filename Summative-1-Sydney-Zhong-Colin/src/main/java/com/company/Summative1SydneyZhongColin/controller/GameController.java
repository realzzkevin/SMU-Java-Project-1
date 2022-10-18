package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.Game;
import com.company.Summative1SydneyZhongColin.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    GameRepository repo;

    //find All
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames(){
        return repo.findAll();
    }
    //findByID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable int id) {
        Optional<Game> returnVal = repo.findById(id);
        if(returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        return repo.save(game);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame (@RequestBody Game game) {
        repo.save(game);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame (@PathVariable int id) {
        repo.deleteById(id);
    }

    @RequestMapping(value = "/studio/{studio}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String Studio){
        return repo.findByStudio(Studio);
    }


    @RequestMapping(value = "/esrb/{esrb}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrb(@PathVariable String esrb){
        return repo.findByEsrbrating(esrb);
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByTitle(@PathVariable String title) {
        return repo.findByTitle(title);
    }

}
