package com.example.number_generator.controller;

import com.example.number_generator.service.CarNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping(path = "/number")
public class CarNumberController {

    @Autowired
    @Qualifier("carNumberServiceImpl")
    private CarNumberService carNumberService;

    @GetMapping(path= "/random")
    public String generateRandomNumber() {
        return carNumberService.generateRandomCarNumber();
    }

    @GetMapping(path= "/next")
    public String generateNextNumber() {
        return carNumberService.generateNextCarNumber();
    }

    @GetMapping(path= "/showAll")
    public Set<String> showAll() {
        return carNumberService.showAll();
    }

}
