package jmasters.spring.model;

import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Course;

@Transactional
public interface CoursesService extends RestService<Course> {

}
