package com.example.repository;

import com.example.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity,Integer> {
    List<StudentCourseEntity> getAllByMark(Integer mark);

    List<StudentCourseEntity> getByCreatedDateIsAfter(LocalDate fromDate);

    List<StudentCourseEntity> getAllByStudentId(Integer id);

    List<StudentCourseEntity> getAllByCourseId(Integer id);
    List<StudentCourseEntity> getAllByStudentIdAndCreatedDate(Integer id, LocalDate fromDate);

    List<StudentCourseEntity> getAllByStudentIdAndCreatedDateBetween(Integer integer, LocalDate fromDate,LocalDate dateTo);
    List<StudentCourseEntity> getAllByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer id, Integer courseId);
    List<StudentCourseEntity> findTop1ByStudentIdOrderByMarkAsc(Integer student_id);

    List<StudentCourseEntity> findTop3ByStudentIdOrderByMarkDesc(Integer student_id);
    List<StudentCourseEntity> findTop1ByStudentIdOrderByCreatedDate(Integer student_id);
    List<StudentCourseEntity> findTop1ByStudentIdAndAndCourseIdOrderByCreatedDate(Integer courseId, Integer student_id);
    List<StudentCourseEntity> findTop1ByStudentIdAndAndCourseIdOrderByMarkDesc(Integer courseId, Integer student_id);
    @Query(value = "select avg(s.mark) from studentcourse as s where student_id=:sid ",nativeQuery = true)
    Double orderAvgByMark(@Param("sid") Integer id);
    @Query(value = "select avg(s.mark) from studentcourse as s where student_id = :sid and course_id=:cid ",nativeQuery = true)
    Double avgByMarkStudentAndCourseId(@Param("sid") Integer id, @Param("cid") Integer cid );

    @Query(value = "select s.mark from studentcourse as s where course_id=:sid and mark > :mark",nativeQuery = true)
    List<Integer> orderMaxByMarkStudentId(@Param("sid") Integer id,@Param("mark") Integer mark);

    @Query(value = "select max(s.mark) from studentcourse as s where course_id=:sid ",nativeQuery = true)
    Integer orderMaxByMarkCourseId(@Param("sid") Integer id);
    @Query(value = "select avg(s.mark) from studentcourse as s where course_id=:sid ",nativeQuery = true)
    Integer avgByMarkStudentAndCourseId(@Param("sid") Integer id);
    @Query(value = "select count(s.mark) from studentcourse as s where course_id=:sid ",nativeQuery = true)
    Integer countByMarkStudentAndCourseId(@Param("sid") Integer id);


}
