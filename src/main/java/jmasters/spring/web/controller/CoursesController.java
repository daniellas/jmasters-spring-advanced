package jmasters.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jmasters.spring.entity.Course;
import jmasters.spring.model.CoursesService;
import jmasters.spring.model.RestService;

@RestController
@RequestMapping("/courses")
public class CoursesController extends RestControllerBase<Course> {

    @Autowired
    private CoursesService service;

    @Override
    protected RestService<Course> service() {
        return service;
    }

}
