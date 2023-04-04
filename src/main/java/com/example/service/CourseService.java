package com.example.service;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;
    public CourseDTO create(CourseDTO dto) {

        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDate.now());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        if (dto.getName().isBlank()) {
            throw new AppBadRequestException("Not found name:");
        }
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;

    }

    public CourseEntity getById(Integer id) {
        Optional<CourseEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + id);
        }
        return optional.get();
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> entityIterable = repository.findAll();
        List<CourseDTO> list = new LinkedList<>();
        entityIterable.forEach(studentEntity -> {
            CourseDTO dto = new CourseDTO();
            dto.setName(studentEntity.getName());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setDuration(studentEntity.getDuration());
            dto.setPrice(studentEntity.getPrice());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public Boolean update(Integer id, CourseDTO courseDTO) {
        CourseEntity entity = getById(id);
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreatedDate(LocalDate.now());
        repository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        CourseEntity entity = getById(id);
        repository.delete(entity);
        return true;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> entityList = repository.getAllByName(name);
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public List<CourseDTO> getPrice(double price) {
        List<CourseEntity> entityList = repository.getAllByPrice(price);
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public List<CourseDTO> getDuration(Integer duration) {
        List<CourseEntity> entityList = repository.getAllByDuration(duration);
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity: entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public List<CourseDTO> getPriceFromAndTo(double fromPrice, double toPrice) {
        List<CourseEntity> entityList = repository.getAllByPriceBetween(fromPrice, toPrice);
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;

    }

    public List<CourseDTO> getTimeFromAndTo(LocalDate fromDate, LocalDate toDate) {
        List<CourseEntity> entityList = repository.getAllByCreatedDateBetween(fromDate, toDate);
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;

    }
}
