package com.example.controller;

import com.example.dto.StudentCourseDTO;
import com.example.dto.StudentCourseResponseDTO;
import com.example.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/studentCourse")
public class StudentCourseController {
    @Autowired
    private StudentCourseService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseDTO dto) {
        StudentCourseDTO studentCourseDTO = service.create(dto);
        return ResponseEntity.ok(studentCourseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getById2(id));
    }

    @GetMapping("/getByStudentId/{id}")
    public ResponseEntity<?> getByStudentId(@PathVariable("id") Integer id) {
        List<StudentCourseDTO> list = service.getByStudentId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByCourseId/{id}")
    public ResponseEntity<?> getByCourseId(@PathVariable("id") Integer id) {
        List<StudentCourseDTO> list = service.getByCourseId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByMark/{id}")
    public ResponseEntity<List<StudentCourseDTO>> getByMark(@PathVariable("id") Integer mark) {
        List<StudentCourseDTO> list = service.getByMark(mark);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/dateFrom")
    public ResponseEntity<List<StudentCourseDTO>> getByFromDate(@RequestParam("from") LocalDate fromDate) {
        List<StudentCourseDTO> list = service.getTimeFrom(fromDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByStudentCourseId/{id}")
    public ResponseEntity<?> getByStudentCourseId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getByIdStudentFull(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentCourseDTO>> getAll() {
        List<StudentCourseDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByStudentMark/{id}")
    public ResponseEntity<List<StudentCourseDTO>> getByStudentMarkFromDate(@PathVariable("id") Integer id,
                                                                           @RequestParam("from") LocalDate fromDate) {
        List<StudentCourseDTO> list = service.getStudentMarkTimeFrom(id, fromDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/date/{id}")
    public ResponseEntity<List<StudentCourseDTO>> getByListFromAndTo(@PathVariable("id") Integer id,
                                                                     @RequestParam("dateFrom") LocalDate fromDate,
                                                                     @RequestParam("dateTo") LocalDate dateTo) {
        List<StudentCourseDTO> list = service.getStudentIdTimeFromAndTo(id, fromDate, dateTo);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/sortCreatedDate")
    public ResponseEntity<?> getBySortCreatedDate(@RequestParam("studentId") Integer id, @RequestParam("courseId") Integer courseId) {
        return ResponseEntity.ok(service.getCourseMark(id, courseId));
    }

    @GetMapping("/finalMarkAndCourse/{id}")
    public ResponseEntity<List<StudentCourseResponseDTO>> getByThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseResponseDTO> list = service.getStudentIdThreeMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/finalBigMarkAndCourse/{id}")
    public ResponseEntity<List<StudentCourseDTO>> getByBigThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseDTO> list = service.getStudentIdBigThreeMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/firstMarkAndCourse/{id}")
    public ResponseEntity<List<StudentCourseDTO>> getByFirstThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseDTO> list = service.getStudentIdFirstThreeMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getCourseIdFirstMArk")
    public ResponseEntity<?> getCourseIdFirstMArk(@RequestParam("courseId") Integer id, @RequestParam("studentId") Integer courseId) {
        return ResponseEntity.ok(service.getCourseIdFirstMark(courseId, id));
    }

    @GetMapping("/getCourseIdBigFirstMArk")
    public ResponseEntity<?> getCourseIdBigFirstMArk(@RequestParam("courseId") Integer id, @RequestParam("studentId") Integer courseId) {
        return ResponseEntity.ok(service.getCourseIdBigFirstMark(courseId, id));
    }

    @GetMapping("/getAvgMarkAndCourse/{id}")
    public ResponseEntity<?> getAvgMarkAndCourse(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getStudentIdAvgMarkAndCourse(id));
    }

    @GetMapping("/getAvgMarkAndCourseId")
    public ResponseEntity<?> getAvgMarkAndCourseId(@RequestParam("id") Integer id, @RequestParam("cid") Integer cid) {
        return ResponseEntity.ok(service.getStudentIdAvgMarkAndCourseIdgjasg(id, cid));
    }

    //    18-----------
    @GetMapping("/getMaxMarkAnStudentIdMark")
    public ResponseEntity<List<Integer>> getMaxMarkAnStudentIdMark(@RequestParam("id") Integer id, @RequestParam("mark") Integer mark) {
        return ResponseEntity.ok(service.getStudentIdMaxMarkAndStudentMark(id, mark));
    }

    //    19-------------
    @GetMapping("/getMaxMarkAndCourseId/{id}")
    public ResponseEntity<?> getMaxMarkAndCourseId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getStudentIdMaxMarkAndCourseId(id));
    }

    //   20---------------
    @GetMapping("/getAvgMarkCourseId/{id}")
    public ResponseEntity<?> getAvgMarkCourseId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getStudentIdAvgMarkAndCourseIdgjasg(id));
    }
//    21------------------
@GetMapping("/getAvgMarkCourseIdCount/{id}")
public ResponseEntity<?> getAvgMarkCourseIdCount(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getStudentIdAvgMarkAndCourseIdCount(id));
}
}
