package jmasters.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jmasters.spring.entity.Course;
import jmasters.spring.repository.CourseRepository;

@Service
public class DefaultCoursesService extends RestServiceBase<Course> implements CoursesService {

    @Autowired
    private CourseRepository repo;

    @Override
    public JpaRepository<Course, Long> repository() {
        return repo;
    }

}
