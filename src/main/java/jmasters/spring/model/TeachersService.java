package jmasters.spring.model;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Teacher;
import jmasters.spring.security.UserRole;

@Transactional
public interface TeachersService {

    List<Teacher> list();

    List<Teacher> findByName(String name);

    @Secured(UserRole.ROLE_ADMIN)
    List<Teacher> createRandom();

    Teacher get(Long id);

    @Secured(UserRole.ROLE_ADMIN)
    Teacher save(Teacher teacher);

    @Secured(UserRole.ROLE_ADMIN)
    void remove(Long id);
}
