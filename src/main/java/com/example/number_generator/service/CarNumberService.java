package com.example.number_generator.service;


import java.util.Set;

public interface CarNumberService {
    String generateRandomCarNumber();
    String generateNextCarNumber();
    Set<String> showAll();
}
