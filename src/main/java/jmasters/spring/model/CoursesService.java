package jmasters.spring.model;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import jmasters.spring.entity.Course;
import jmasters.spring.security.UserRole;

@Transactional
public interface CoursesService {
    List<Course> list();

    @Secured(UserRole.ROLE_ADMIN)
    Course get(Long id);
}
