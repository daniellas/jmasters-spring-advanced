package jmasters.spring.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Teacher;

@Transactional
public interface TeachersService extends RestService<Teacher> {

    List<Teacher> findByName(String name);

    List<Teacher> createRandom();

}
