package com.example.number_generator.repository;

import com.example.number_generator.model.CarNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CarNumberRepository extends CrudRepository<CarNumber, Long> {

CarNumber findFirstByIsRandomFalseOrderByCreateAtDesc();
CarNumber findByFullCarNumber(String fullCarNumber);

}
