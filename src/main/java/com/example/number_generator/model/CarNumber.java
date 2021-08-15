package com.example.number_generator.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstLetter", unique = false, nullable = false)
    private String firstLetter;

    @Column(name = "number", unique = false, nullable = false)
    private Integer number;

    @Column(name = "secondLetter", unique = false, nullable = false)
    private String secondLetter;

    @Column(name = "thirdLetter", unique = false, nullable = false)
    private String thirdLetter;

    @Column(name = "region", unique = false, nullable = false)
    private static final String REGION = "116 RUS";

    @Column(name = "isRandom", unique = false, nullable = false)
    private Boolean isRandom;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @Column(name = "fullCarNumber", unique = true, nullable = false)
    private String fullCarNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarNumber carNumber = (CarNumber) o;
        return Objects.equals(id, carNumber.id) && Objects.equals(firstLetter, carNumber.firstLetter) && Objects.equals(number, carNumber.number) && Objects.equals(secondLetter, carNumber.secondLetter) && Objects.equals(thirdLetter, carNumber.thirdLetter) && Objects.equals(REGION, carNumber.REGION) && Objects.equals(isRandom, carNumber.isRandom) && Objects.equals(createAt, carNumber.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstLetter, number, secondLetter, thirdLetter, REGION, isRandom, createAt);
    }


}


