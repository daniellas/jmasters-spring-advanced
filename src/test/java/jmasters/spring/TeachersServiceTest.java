package jmasters.spring;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmasters.spring.config.JpaConfig;
import jmasters.spring.config.RepositoryConfig;
import jmasters.spring.config.ServiceConfig;
import jmasters.spring.entity.Teacher;
import jmasters.spring.model.TeachersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class, RepositoryConfig.class, ServiceConfig.class })
public class TeachersServiceTest {

    @Autowired
    private TeachersService teachersService;

    @Test
    public void insertMultipleTimesShouldSuccess() {
        for (int i = 0; i < 10; i++) {
            List<Teacher> result = teachersService.createRandom();

            Assert.assertEquals(i + 1, result.size());
        }
    }

}
