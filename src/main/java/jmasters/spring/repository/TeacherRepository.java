package jmasters.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jmasters.spring.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
