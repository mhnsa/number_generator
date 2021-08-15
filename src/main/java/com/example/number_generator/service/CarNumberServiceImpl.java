package com.example.number_generator.service;


import com.example.number_generator.model.CarNumber;
import com.example.number_generator.repository.CarNumberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Service
public class CarNumberServiceImpl implements CarNumberService {

    private final CarNumberRepository carNumberRepository;
    private final String REGION = "116 RUS";
    private final long MAX_COUNT_OF_ISSUED_CAR_NUMBERS = 1728000;
    static String[] carLetters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
    private int number;
    private String firstLetter;
    private String secondLetter;
    private String thirdLetter;
    Random generator = new Random();

    public CarNumberServiceImpl(CarNumberRepository carNumberRepository) {
        this.carNumberRepository = carNumberRepository;
    }

    @Override
    public CarNumber generateRandomCarNumber()  {
        number = generator.nextInt(1000);
        firstLetter = generateRandomLetter();
        secondLetter = generateRandomLetter();
        thirdLetter = generateRandomLetter();

        CarNumber carNumber = CarNumber.builder()
                .firstLetter(firstLetter)
                .number(number)
                .secondLetter(secondLetter)
                .thirdLetter(thirdLetter)
                .isRandom(true)
                .fullCarNumber(firstLetter + String.format("%03d", number) + secondLetter + thirdLetter + REGION)
                .createAt(LocalDateTime.now())
                .build();
        return carNumber;
    }

    @Override
    public String generateTextRandomCarNumber() {
        boolean needToGenerateCarNumber = true;
        if (carNumberRepository.count() < MAX_COUNT_OF_ISSUED_CAR_NUMBERS) {
            while(needToGenerateCarNumber) {
                CarNumber carNumber = generateRandomCarNumber();
                String fullCarNumber = carNumber.getFullCarNumber();
                if (carNumberRepository.findByFullCarNumber(fullCarNumber) != null) {
                    continue;
                } else {
                    carNumberRepository.save(carNumber);
                    needToGenerateCarNumber = false;
                    return fullCarNumber;
                }
            }
        }
        return "Все номера выданы";
    }


    @Override
    public CarNumber generateNextCarNumber() {

        CarNumber lastIssuedCarNumberByNext = carNumberRepository.findFirstByIsRandomFalseOrderByCreateAtDesc();

        if (lastIssuedCarNumberByNext != null) {
            number = lastIssuedCarNumberByNext.getNumber();
            firstLetter = lastIssuedCarNumberByNext.getFirstLetter();
            int indexOfFirstLetter = Arrays.binarySearch(carLetters, firstLetter);
            secondLetter = lastIssuedCarNumberByNext.getSecondLetter();
            int indexOfSecondLetter = Arrays.binarySearch(carLetters, secondLetter);
            thirdLetter = lastIssuedCarNumberByNext.getThirdLetter();
            int indexOfThirdLetter = Arrays.binarySearch(carLetters, thirdLetter);
            number = generateNextNumber(number);

            thirdLetter = generateNextThirdLetter(indexOfThirdLetter,number);
            secondLetter = generateNextSecondLetter(indexOfSecondLetter, indexOfThirdLetter, number);
            firstLetter = generateNextFirstLetter(indexOfFirstLetter, indexOfSecondLetter, number);
        } else {
            number = 0;
            thirdLetter = "А";
            secondLetter = "А";
            firstLetter = "А";
        }
        CarNumber carNumber = CarNumber.builder()
                .firstLetter(firstLetter)
                .number(number)
                .secondLetter(secondLetter)
                .thirdLetter(thirdLetter)
                .isRandom(false)
                .createAt(LocalDateTime.now())
                .fullCarNumber(firstLetter + String.format("%03d", number) + secondLetter + thirdLetter + REGION)
                .build();
        return carNumber;
    }

    @Override
    public String generateTextNextCarNumber() {
        boolean needToGenerateCarNumber = true;
        if (carNumberRepository.count() < MAX_COUNT_OF_ISSUED_CAR_NUMBERS) {
            while (needToGenerateCarNumber) {
                CarNumber carNumber = generateNextCarNumber();
                String fullCarNumber = carNumber.getFullCarNumber();
                if (carNumberRepository.findByFullCarNumber(fullCarNumber) != null) {
                    continue;
                } else {
                    needToGenerateCarNumber = false;
                    carNumberRepository.save(carNumber);
                    return fullCarNumber;
                }
            }
        }
        return "Все номера выданы";
    }

    private String generateRandomLetter() {
        return String.valueOf(carLetters[generator.nextInt(carLetters.length)]);
    }

    public static Integer generateNextNumber(int number) {
        if (number == 999) {
            return 0;
        } else {
            return ++number;
        }
    }
    public static String generateNextFirstLetter(int indexOfFirstLetter, int indexOfSecondLetter, int number) {
           if (number < 999) {
               return carLetters[indexOfFirstLetter];
           } else if (indexOfSecondLetter < 11){
               return carLetters[indexOfFirstLetter];
           } else if (indexOfFirstLetter < 11) {
               return carLetters[++indexOfFirstLetter];
           } else {
               return carLetters[0];
           }
    }
    public static String generateNextSecondLetter(int indexOfSecondLetter, int indexOfThirdLetter, int number) {
        if (number < 999) {
            return carLetters[indexOfSecondLetter];
        } else if (indexOfThirdLetter < 11){
            return carLetters[indexOfSecondLetter];
        } else if (indexOfSecondLetter < 11) {
            return carLetters[++indexOfSecondLetter];
        } else {
            return carLetters[0];
        }

    }
    public static String generateNextThirdLetter(int indexOfThirdLetter, int number) {
        if (number < 999) {
            return carLetters[indexOfThirdLetter];
        } else if (indexOfThirdLetter < 11){
            return carLetters[++indexOfThirdLetter];
        } else {
            return carLetters[0];
        }
    }
}
