package jmasters.spring;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import jmasters.spring.config.JpaConfig;
import jmasters.spring.config.RepositoryConfig;
import jmasters.spring.entity.Course;
import jmasters.spring.repository.CourseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class, RepositoryConfig.class })
@TransactionConfiguration(defaultRollback = false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext
public class RepositoryTest {

    @Autowired
    private CourseRepository courseRepo;

    private static Long id;

    @Test
    public void test00CourseRepositoryShouldBeInjected() {
        Assert.assertNotNull(courseRepo);
    }

    @Test
    public void test01CourseShouldBeInserted() {
        Course course = new Course();

        course.setName("j. Angielski");

        courseRepo.save(course);

        Assert.assertEquals(new Long(1l), course.getId());

        id = course.getId();
    }

    @Test
    public void test02CourseShouldBeFoundById() {
        Course course = courseRepo.findOne(id);

        Assert.assertEquals(id, course.getId());
        Assert.assertEquals("j. Angielski", course.getName());
    }

    @Test
    public void test03CoursesNumberShouldBeOne() {
        List<Course> courses = courseRepo.findAll();

        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void test04SecondCourseShouldBeInserted() {
        Course course = new Course();

        course.setName("Matematyka");

        courseRepo.save(course);
    }

    @Test
    public void test05CoursesShouldbeFoundByName() {
        List<Course> courses = courseRepo.findByName("Matematyka");

        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void test06CoursesShouldbeFoundByNameLike() {
        List<Course> courses = courseRepo.findByNameContains("Mate");

        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void test07CoursesCountShouldBe2() {
        long count = courseRepo.count();

        Assert.assertEquals(2, count);
    }

    @Test
    public void test08CoursesCountBynameShouldSuccess() {
        List<Object[]> countByName = courseRepo.findCountByName();

        Assert.assertEquals("j. Angielski", countByName.get(0)[0]);
        Assert.assertEquals(new Long(1), countByName.get(0)[1]);
        Assert.assertEquals("Matematyka", countByName.get(1)[0]);
        Assert.assertEquals(new Long(1), countByName.get(1)[1]);
    }

    @Test
    public void test09FindBelowIdShouldReturnOneRow() {
        List<Course> courses = courseRepo.findBelowId(2l);

        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void test10FindWithSortShouldSuccess() {
        List<Course> courses = courseRepo.findAll(new Sort(Sort.Direction.DESC, "id"));

        Assert.assertEquals(new Long(2l), courses.get(0).getId());
        Assert.assertEquals(new Long(1l), courses.get(1).getId());
    }

    @Test
    public void test11FindPagedShouldSuccess() {
        Page<Course> courses = courseRepo.findAll(new PageRequest(0, 1));

        Assert.assertEquals(2, courses.getTotalElements());
        Assert.assertEquals(1, courses.getNumberOfElements());
        Assert.assertEquals(2, courses.getTotalPages());
        Assert.assertEquals(1, courses.getContent().size());
    }

}
