package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO dto) {
        StudentDTO studentDTO = service.create(dto);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(service.update(id, studentDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getName/{name}")
    public ResponseEntity<List<StudentDTO>> getName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.getName(name));
    }
    @GetMapping("/getSurname/{surname}")
    public ResponseEntity<List<StudentDTO>> getSurname(@PathVariable("surname") String surname) {
        return ResponseEntity.ok(service.getSurname(surname));
    }
    @GetMapping("/getLevel/{level}")
    public ResponseEntity<List<StudentDTO>> getLevel(@PathVariable("level") Integer level) {
        return ResponseEntity.ok(service.getLevel(level));
    }
    @GetMapping("/getAge/{age}")
    public ResponseEntity<List<StudentDTO>> getAge(@PathVariable("age") Integer age) {
        return ResponseEntity.ok(service.getAge(age));
    }
    @GetMapping("/getGender/{gender}")
    public ResponseEntity<List<StudentDTO>> getGender(@PathVariable("gender") String gender) {
        return ResponseEntity.ok(service.getByGender(gender));
    }

    @GetMapping("/dateFrom")
    public ResponseEntity<List<StudentDTO>> getByFromDate(@RequestParam("from")  LocalDate fromDate) {
        List<StudentDTO> list = service.getTimeFrom(fromDate);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/date")
    public ResponseEntity<List<StudentDTO>> getByListFromAndTo(@RequestParam("dateFrom") LocalDate fromDate,
                                                      @RequestParam("dateTo") LocalDate toDate) {
        List<StudentDTO> list = service.getTimeFromAndTo(fromDate, toDate);
        return ResponseEntity.ok(list);
    }
}
