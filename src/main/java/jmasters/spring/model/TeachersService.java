package jmasters.spring.model;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Teacher;

@Transactional
public interface TeachersService {

    List<Teacher> list();

    List<Teacher> findByName(String name);

    @Secured("ROLE_ADMIN")
    List<Teacher> createRandom();

    Teacher get(Long id);

    @Secured("ROLE_ADMIN")
    Teacher save(Teacher teacher);

    @Secured("ROLE_ADMIN")
    void remove(Long id);
}
