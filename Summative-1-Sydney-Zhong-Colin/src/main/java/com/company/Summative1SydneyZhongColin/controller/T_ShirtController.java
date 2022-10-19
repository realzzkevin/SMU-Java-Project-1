package com.company.Summative1SydneyZhongColin.controller;


import com.company.Summative1SydneyZhongColin.model.T_Shirt;
import com.company.Summative1SydneyZhongColin.repository.T_ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tshirt")
public class T_ShirtController {

    @Autowired
    T_ShirtRepository repo;

    //creating a t-shirt POST
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public T_Shirt addTShirt(@RequestBody T_Shirt t_shirt) {
        return repo.save(t_shirt);
    }

    //Get T-shirt by ID GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T_Shirt getTShirtById(@PathVariable Integer id) {
        Optional<T_Shirt> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    //Get all T-shirts GET
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<T_Shirt> getTShirt() {
        return repo.findAll();
    }

    //updating T-shirt PUT
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody T_Shirt t_shirt) {
        repo.save(t_shirt);
    }

    //Deleting a T-shirt by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    //Find T-shirt by Color
    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<T_Shirt> getTShirtByColor(@PathVariable String color) {
        return repo.findTShirtByColor(color);
    }

    //Find T-Shirt by size
    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<T_Shirt> getTShirtBySize(@PathVariable String size) {
        return repo.findTShirtBySize(size);
    }
}
