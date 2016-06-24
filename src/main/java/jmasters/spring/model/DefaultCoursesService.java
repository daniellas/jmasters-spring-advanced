package jmasters.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmasters.spring.entity.Course;
import jmasters.spring.repository.CourseRepository;

@Service
public class DefaultCoursesService implements CoursesService {

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<Course> list() {
        return courseRepo.findAll();
    }

}
