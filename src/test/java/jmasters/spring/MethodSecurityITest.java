package jmasters.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmasters.spring.config.JpaConfig;
import jmasters.spring.config.RepositoryConfig;
import jmasters.spring.config.SecurityConfig;
import jmasters.spring.config.ServiceConfig;
import jmasters.spring.model.TeachersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class, RepositoryConfig.class, ServiceConfig.class, SecurityConfig.class })
@DirtiesContext
public class MethodSecurityITest {

    @Autowired
    private TeachersService teachersService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void anonnymousAccessShouldBeDenied() {
        teachersService.createRandom();
    }

    @Test(expected = AccessDeniedException.class)
    public void klientAccessShouldBeDenied() {
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken("klient", "klient"));

        teachersService.createRandom();
    }

    @Test
    public void adminAccessShouldBeGranted() {
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));

        teachersService.createRandom();
    }

}
