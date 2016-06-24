package jmasters.spring.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Course;

@Transactional
public interface CoursesService {
    List<Course> list();
}
