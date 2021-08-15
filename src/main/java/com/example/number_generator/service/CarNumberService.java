package com.example.number_generator.service;


import com.example.number_generator.model.CarNumber;

public interface CarNumberService {
    CarNumber generateRandomCarNumber();
    String generateTextRandomCarNumber();
    CarNumber generateNextCarNumber();
    String generateTextNextCarNumber();

}
