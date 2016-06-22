package jmasters.spring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import jmasters.spring.config.JpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class })
public class JpaConfigTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PlatformTransactionManager txMngr;

    @Test
    public void entityManagerShouldBeInjected() {
        Assert.assertNotNull(em);
    }

    @Test
    public void transactionManagerShouldBeInjected() {
        Assert.assertNotNull(txMngr);
    }

}
