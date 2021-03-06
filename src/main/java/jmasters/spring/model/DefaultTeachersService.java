package jmasters.spring.model;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmasters.spring.entity.Course;
import jmasters.spring.entity.Teacher;
import jmasters.spring.repository.CourseRepository;
import jmasters.spring.repository.TeacherRepository;

@Service
public class DefaultTeachersService implements TeachersService {

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<Teacher> createRandom() {
        Teacher teacher = new Teacher();

        teacher.setFirstName(UUID.randomUUID().toString());
        teacher.setLastName(UUID.randomUUID().toString());

        Course course = null;

        if (courseRepo.count() == 0) {
            course = new Course();
            course.setName(UUID.randomUUID().toString());
        } else {
            course = courseRepo.findAll().iterator().next();
        }

        course.addTeacher(teacher);
        courseRepo.save(course);

        return teacherRepo.findAll();
    }

}
