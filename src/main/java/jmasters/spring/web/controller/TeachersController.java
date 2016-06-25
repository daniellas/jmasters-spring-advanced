package jmasters.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jmasters.spring.entity.Teacher;
import jmasters.spring.model.RestService;
import jmasters.spring.model.TeachersService;

@RestController
@RequestMapping("/teachers")
public class TeachersController extends RestControllerBase<Teacher> {

    @Autowired
    private TeachersService teachersService;

    @RequestMapping("/random")
    public List<Teacher> createRandom() {
        return teachersService.createRandom();
    }

    @RequestMapping("/filter")
    public List<Teacher> filter(@RequestParam(required = false) String name) {
        if (name == null) {
            return super.list();
        } else {
            return teachersService.findByName(name);
        }
    }

    @Override
    protected RestService<Teacher> service() {
        return teachersService;
    }

}
