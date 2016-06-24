package jmasters.spring;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmasters.spring.config.JpaConfig;
import jmasters.spring.config.RepositoryConfig;
import jmasters.spring.config.ServiceConfig;
import jmasters.spring.model.TeachersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class, RepositoryConfig.class, ServiceConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext
public class TeachersServiceITest {

    @Autowired
    private TeachersService teachersService;

    @Test
    public void insertMultipleTimesShouldSuccess() {
        for (int i = 0; i < 10; i++) {
            teachersService.createRandom();
        }
    }

}
