package com.example.number_generator.service;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CarNumberServiceImplTest {


    @Test
    public void generateNextNumber() {
        Assertions.assertEquals(0, CarNumberServiceImpl.generateNextNumber(999));
        Assertions.assertEquals(2, CarNumberServiceImpl.generateNextNumber(1));
    }

    @Test
    public void generateNextFirstLetter() {
        Assertions.assertEquals("А", CarNumberServiceImpl.generateNextFirstLetter(0, 0, 999));
        Assertions.assertEquals("В", CarNumberServiceImpl.generateNextFirstLetter(0, 11, 999));
        Assertions.assertEquals("А", CarNumberServiceImpl.generateNextFirstLetter(11, 11, 999));
    }
    @Test
    public void generateNextSecondLetter() {
        Assertions.assertEquals("А", CarNumberServiceImpl.generateNextSecondLetter(11, 11, 999));
        Assertions.assertEquals("Х", CarNumberServiceImpl.generateNextSecondLetter(11, 11, 900));

    }
    @Test
    public void generateNextThirdLetter() {
        Assertions.assertEquals("А", CarNumberServiceImpl.generateNextThirdLetter(11, 999));
        Assertions.assertEquals("Х", CarNumberServiceImpl.generateNextThirdLetter(11, 990));

    }
}
