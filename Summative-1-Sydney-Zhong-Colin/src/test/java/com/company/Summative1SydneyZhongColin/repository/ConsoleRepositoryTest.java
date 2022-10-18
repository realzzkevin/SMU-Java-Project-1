package com.company.Summative1SydneyZhongColin.repository;

import com.company.Summative1SydneyZhongColin.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepository;

    private Console console;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
        console = new Console("Playstation 5", "Sony", 499.99);
    }

    @Test
    public void shouldCreateGetAndDeleteConsole() {
        Console console1 = consoleRepository.save(console);
        Optional<Console> console2 = consoleRepository.findById(console1.getId());
        assertEquals(console2.get(), console1);

        consoleRepository.deleteById(console1.getId());
        console2 = consoleRepository.findById(console1.getId());
        assertFalse(console2.isPresent());
    }

    @Test
    public void shouldUpdateConsole() {
        consoleRepository.save(console);
        console.setModel("PS5");
        consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getId());
        assertEquals(console1.get(), console);
    }

    @Test
    public void shouldFindByManufacturer() {
        console = consoleRepository.save(console);
        List<Console> consoleList = consoleRepository.findAllConsolesByManufacturer("Sony");
        assertEquals(consoleList.get(0), console);
    }
}