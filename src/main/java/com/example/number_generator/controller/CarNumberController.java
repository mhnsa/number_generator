package com.example.number_generator.controller;

import com.example.number_generator.service.CarNumberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(path = "/number")
public class CarNumberController {

    private final CarNumberService carNumberService;

    public CarNumberController(CarNumberService carNumberService) {
        this.carNumberService = carNumberService;
    }

    @GetMapping(path= "/random")
    public String generateRandomNumber()  {
        return carNumberService.generateTextRandomCarNumber();
    }

    @GetMapping(path= "/next")
    public String generateNextNumber() {
        return carNumberService.generateTextNextCarNumber();
    }



}
