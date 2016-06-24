package jmasters.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jmasters.spring.entity.Teacher;
import jmasters.spring.model.TeachersService;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    @Autowired
    private TeachersService teachersService;

    @RequestMapping("/random")
    public List<Teacher> createRandom() {
        return teachersService.createRandom();
    }

    @RequestMapping
    public List<Teacher> list(@RequestParam(required = false) String name) {
        if (name == null) {
            return teachersService.list();
        } else {
            return teachersService.findByName(name);
        }
    }

    @RequestMapping(value = "/{id}")
    public Teacher get(@PathVariable Long id) {
        return teachersService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Teacher save(@RequestBody Teacher teacher) {
        return teachersService.save(teacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        teachersService.remove(id);
    }

}
