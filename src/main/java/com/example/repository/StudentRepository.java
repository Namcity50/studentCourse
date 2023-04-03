package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {

    List<StudentEntity> getAllByName(String name);
    List<StudentEntity> getByCreatedDateIsAfter(LocalDate fromDate);
    List<StudentEntity> getByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
