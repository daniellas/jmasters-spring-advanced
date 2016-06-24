package jmasters.spring.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Teacher;

@Transactional
public interface TeachersService {

    List<Teacher> list();

    List<Teacher> findByName(String name);

    List<Teacher> createRandom();

    Teacher get(Long id);

    Teacher save(Teacher teacher);

    void remove(Long id);
}
