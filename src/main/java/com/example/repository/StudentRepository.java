package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    List<StudentEntity> getAllByName(String name);

    List<StudentEntity> getAllBySurname(String name);

    List<StudentEntity> getAllByLevel(Integer level);

    List<StudentEntity> getAllByAge(Integer age);

    List<StudentEntity> getAllByGender(String gender);

    List<StudentEntity> getByCreatedDateIsAfter(LocalDate fromDate);

    List<StudentEntity> getByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
