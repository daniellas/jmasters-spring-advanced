package jmasters.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import jmasters.spring.entity.Course;
import jmasters.spring.web.controller.RestControllerBase;

@RequestMapping("/courses")
public class CoursesController extends RestControllerBase<Course> {

    @Autowired
    private CoursesService service;

    @Override
    protected RestService<Course> service() {
        return service;
    }

}
