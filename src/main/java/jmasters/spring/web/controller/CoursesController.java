package jmasters.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jmasters.spring.entity.Course;
import jmasters.spring.model.CoursesService;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @RequestMapping
    public List<Course> list() {
        return coursesService.list();
    }

    @RequestMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return coursesService.get(id);
    }

}
