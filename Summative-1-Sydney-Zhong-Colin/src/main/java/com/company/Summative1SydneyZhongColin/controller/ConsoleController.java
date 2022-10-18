package com.company.Summative1SydneyZhongColin.controller;

import com.company.Summative1SydneyZhongColin.model.Console;
import com.company.Summative1SydneyZhongColin.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/console")
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    // create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console console) {
        return consoleRepository.save(console);
    }

    // read by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable Integer id) {
        return consoleRepository.findById(id).orElse(null);
    }

    // read all
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    // update
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        consoleRepository.save(console);
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        consoleRepository.deleteById(id);
    }

    // read by manufacturer
    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleRepository.findAllConsolesByManufacturer(manufacturer);
    }

}
