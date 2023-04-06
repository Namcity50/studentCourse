package com.example.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseResponseDTO {
    private Integer id;
    private CourseResponseDTO course;
    private StudentResponseDTO student;
    private Integer mark;
    private LocalDate createdDate;
}
