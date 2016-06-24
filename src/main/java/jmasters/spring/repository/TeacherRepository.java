package jmasters.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jmasters.spring.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t where t.firstName like %:name% or t.lastName like %:name%")
    List<Teacher> findByName(@Param("name") String name);
}
