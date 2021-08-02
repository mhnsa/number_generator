package com.example.number_generator.service;


import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CarNumberServiceImpl implements CarNumberService {

    Set<String> issuedCarNumbers = new HashSet<>();
    char[] carLetters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
    private int number;

    private int firstLetterIndex;
    private int secondLetterIndex;
    private int thirdLetterIndex;
    private final String REGION = " 116 RUS";

    String lastIssuedNumber = null;
    Random generator = new Random();

    @Override
    public String generateRandomCarNumber() {
        String randomCarNumber = "" + generateFirstLetter() + generateNumber() + generateSecondLetter() + generateThirdLetter() + REGION;
        lastIssuedNumber = randomCarNumber;
        if (issuedCarNumbers.add(randomCarNumber)) {
            return randomCarNumber;
        } else {
           return generateRandomCarNumber();
        }
    }

    @Override
    public String generateNextCarNumber() {
        if (number != 999) {
            number++;
        } else if (thirdLetterIndex != carLetters.length) {
            number = 0;
            thirdLetterIndex++;
        } else if (secondLetterIndex != carLetters.length) {
            thirdLetterIndex = 0;
            secondLetterIndex++;
        } else if (firstLetterIndex != carLetters.length) {
            thirdLetterIndex = 0;
            secondLetterIndex = 0;
            firstLetterIndex++;
        } else {
            thirdLetterIndex = 0;
            secondLetterIndex = 0;
            firstLetterIndex = 0;
        }
        String nextNumber = "" + carLetters[firstLetterIndex] + convertNumberToString(number) +
        carLetters[secondLetterIndex] + carLetters[thirdLetterIndex] + REGION;

        if (issuedCarNumbers.add(nextNumber)) {
            return nextNumber;
        } else {
            System.out.println("This car number has been already issued");
            return generateRandomCarNumber();
        }
    }

    @Override
    public Set<String> showAll() {
        return issuedCarNumbers;
    }

    public char generateFirstLetter() {
        firstLetterIndex = generator.nextInt(carLetters.length);
        return carLetters[firstLetterIndex];
    }

    public char generateSecondLetter() {
        secondLetterIndex = generator.nextInt(carLetters.length);
        return carLetters[secondLetterIndex];
    }

    public char generateThirdLetter() {
        thirdLetterIndex = generator.nextInt(carLetters.length);
        return carLetters[thirdLetterIndex];
    }

    public String generateNumber() {
        number = generator.nextInt(1000);
        return convertNumberToString(number);
    }

    public String convertNumberToString(int number) {
        if (this.number < 10) {
            return String.format("00%d", this.number);
        } else if (this.number < 100) {
            return String.format("0%d", this.number);
        } else {
            return String.format("%d", this.number);
        }
    }
}
