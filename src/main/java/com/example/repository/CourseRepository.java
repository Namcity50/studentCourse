package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity,Integer> {
    List<CourseEntity> getAllByName(String name);

    List<CourseEntity> getAllByPrice(double price);

    List<CourseEntity> getAllByDuration(Integer fromDate);

    List<CourseEntity> getAllByPriceBetween(double fromPrice, double toPrice);

    List<CourseEntity> getAllByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
