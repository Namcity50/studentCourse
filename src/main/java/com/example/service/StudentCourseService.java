package com.example.service;

import com.example.dto.*;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository repository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public StudentCourseDTO create(StudentCourseDTO dto) {
        StudentEntity existS = studentService.getById(dto.getStudentId());
        CourseEntity existC = courseService.getById(dto.getCourseId());
        StudentCourseEntity entity = new StudentCourseEntity();
        entity.setCourse(existC);
        entity.setStudent(existS);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDate.now());
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(Integer id, StudentCourseDTO dto) {
        StudentCourseEntity entity = getById(id);
        StudentEntity existS = studentService.getById(dto.getStudentId());
        CourseEntity existC = courseService.getById(dto.getCourseId());
        entity.setStudent(existS);
        entity.setCourse(existC);
        entity.setMark(dto.getMark());
        repository.save(entity);
        return true;
    }

    public StudentCourseDTO getById2(Integer id) {
        StudentCourseEntity entity = getById(id);
        StudentCourseDTO dto = new StudentCourseDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private StudentCourseEntity getById(Integer id) {
        Optional<StudentCourseEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        return optional.get();
    }

    public StudentCourseResponseDTO getByIdStudentFull(Integer id) {
        StudentCourseEntity entity = getById(id);
        StudentCourseResponseDTO response = new StudentCourseResponseDTO();
        response.setId(entity.getId());
        response.setMark(entity.getMark());
        response.setCourse(new CourseResponseDTO(entity.getCourse().getId(), entity.getCourse().getName()));
        response.setStudent(new StudentResponseDTO(entity.getStudent().getId(),
                entity.getStudent().getName(), entity.getStudent().getSurname()));
        response.setCreatedDate(entity.getCreatedDate());
        return response;
    }

    public List<StudentCourseDTO> getByStudentId(Integer id) {
        List<StudentCourseDTO> list = new LinkedList<>();
        List<StudentCourseEntity> entity = repository.getAllByStudentId(id);
        entity.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(studentCourseEntity.getId());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;

    }

    public List<StudentCourseDTO> getByCourseId(Integer id) {
        List<StudentCourseEntity> entity = repository.getAllByCourseId(id);
        List<StudentCourseDTO> list = new LinkedList<>();
        entity.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(studentCourseEntity.getId());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getByMark(Integer mark) {
        List<StudentCourseEntity> entityList = repository.getAllByMark(mark);
        List<StudentCourseDTO> list = new LinkedList<>();
        for (StudentCourseEntity entity : entityList) {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public List<StudentCourseDTO> getTimeFrom(LocalDate fromDate) {
        List<StudentCourseEntity> entityList = repository.getByCreatedDateIsAfter(fromDate);
        List<StudentCourseDTO> list = new LinkedList<>();
        for (StudentCourseEntity entity : entityList) {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public Boolean delete(Integer id) {
        StudentCourseEntity entity = getById(id);
        repository.delete(entity);
        return true;
    }

    public List<StudentCourseDTO> getAll() {
        Iterable<StudentCourseEntity> entityIterable = repository.findAll();
        List<StudentCourseDTO> list = new LinkedList<>();
        entityIterable.forEach(studentEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(studentEntity.getId());
            dto.setStudentId(studentEntity.getStudent().getId());
            dto.setCourseId(studentEntity.getCourse().getId());
            dto.setMark(studentEntity.getMark());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getStudentMarkTimeFrom(Integer id, LocalDate fromDate) {
        List<StudentCourseEntity> entity = repository.getAllByStudentIdAndCreatedDate(id, fromDate);
        List<StudentCourseDTO> list = new LinkedList<>();
        entity.forEach(studentEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(studentEntity.getId());
            dto.setStudentId(studentEntity.getStudent().getId());
            dto.setCourseId(studentEntity.getCourse().getId());
            dto.setMark(studentEntity.getMark());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getStudentIdTimeFromAndTo(Integer id, LocalDate fromDate, LocalDate dateTo) {
        List<StudentCourseEntity> entity = repository.getAllByStudentIdAndCreatedDateBetween(id, fromDate, dateTo);
        List<StudentCourseDTO> list = new LinkedList<>();
        entity.forEach(studentEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(studentEntity.getId());
            dto.setStudentId(studentEntity.getStudent().getId());
            dto.setCourseId(studentEntity.getCourse().getId());
            dto.setMark(studentEntity.getMark());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getCourseMark(Integer id, Integer courseId) {
        List<StudentCourseEntity> entityList = repository.getAllByStudentIdAndCourseIdOrderByCreatedDateDesc(id, courseId);
        List<StudentCourseDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setMark(studentCourseEntity.getMark());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseResponseDTO> getStudentIdThreeMark(Integer id) {
        List<StudentCourseEntity> entityList = repository.findTop1ByStudentIdOrderByMarkAsc(id);
        List<StudentCourseResponseDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseEntity -> {
            StudentCourseResponseDTO dto = new StudentCourseResponseDTO();
            dto.setMark(studentCourseEntity.getMark());
            dto.setStudent(new StudentResponseDTO(studentCourseEntity.getStudent().getId(),
                    studentCourseEntity.getStudent().getName(), studentCourseEntity.getStudent().getSurname()));
            dto.setCourse(new CourseResponseDTO(studentCourseEntity.getCourse().getId(), studentCourseEntity.getCourse().getName()));
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getStudentIdBigThreeMark(Integer id) {
        List<StudentCourseEntity> entityList = repository.findTop3ByStudentIdOrderByMarkDesc(id);
        List<StudentCourseDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setMark(studentCourseEntity.getMark());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getStudentIdFirstThreeMark(Integer id) {
        List<StudentCourseEntity> entityList = repository.findTop1ByStudentIdOrderByCreatedDate(id);
        List<StudentCourseDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setMark(studentCourseEntity.getMark());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getCourseIdFirstMark(Integer courseId, Integer id) {
        List<StudentCourseEntity> entityList = repository.findTop1ByStudentIdAndAndCourseIdOrderByCreatedDate(courseId, id);
        List<StudentCourseDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setMark(studentCourseEntity.getMark());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentCourseDTO> getCourseIdBigFirstMark(Integer courseId, Integer id) {
        List<StudentCourseEntity> entityList = repository.findTop1ByStudentIdAndAndCourseIdOrderByMarkDesc(courseId, id);
        List<StudentCourseDTO> list = new LinkedList<>();
        entityList.forEach(studentCourseEntity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setMark(studentCourseEntity.getMark());
            dto.setStudentId(studentCourseEntity.getStudent().getId());
            dto.setCourseId(studentCourseEntity.getCourse().getId());
            dto.setCreatedDate(studentCourseEntity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public Double getStudentIdAvgMarkAndCourse(Integer id) {
        Double avg = repository.orderAvgByMark(id);
        return avg;
    }

    public Double getStudentIdAvgMarkAndCourseIdgjasg(Integer id, Integer cid) {
        Double avg = repository.avgByMarkStudentAndCourseId(id,cid);
        return avg;
    }

    public List<Integer> getStudentIdMaxMarkAndStudentMark(Integer id, Integer mark) {
        List<Integer> max = repository.orderMaxByMarkStudentId(id,mark);
        return max;
    }
    public Integer getStudentIdMaxMarkAndCourseId(Integer id) {
        Integer avg = repository.orderMaxByMarkCourseId(id);
        return avg;
    }
    public Integer getStudentIdAvgMarkAndCourseIdgjasg(Integer id) {
        Integer avg = repository.avgByMarkStudentAndCourseId(id);
        return avg;
    }
    public Integer getStudentIdAvgMarkAndCourseIdCount(Integer id) {
        Integer avg = repository.countByMarkStudentAndCourseId(id);
        return avg;
    }
}
