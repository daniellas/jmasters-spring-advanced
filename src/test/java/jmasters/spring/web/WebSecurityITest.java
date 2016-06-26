package jmasters.spring.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jmasters.spring.config.JpaConfig;
import jmasters.spring.config.RepositoryConfig;
import jmasters.spring.config.SecurityConfig;
import jmasters.spring.config.ServiceConfig;
import jmasters.spring.web.config.MvcConfig;
import jmasters.spring.web.config.WebSecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { JpaConfig.class, RepositoryConfig.class, ServiceConfig.class, MvcConfig.class,
        SecurityConfig.class, WebSecurityConfig.class })
@DirtiesContext
public class WebSecurityITest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webCtx;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).addFilters(springSecurityFilterChain).build();
    }

    @Test
    public void coursesAccessShouldBeGrantedForKlient() throws Exception {
        mockMvc.perform(get("/courses").with(user("klient").password("klient")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void teachersAccessShouldBeDeniedForAnonymous() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andDo(print())
                .andExpect(status().is(302));
    }

    @Test
    public void teachersAccessShouldBeGrantedForKlient() throws Exception {
        mockMvc.perform(get("/teachers").with(user("klient").password("klient")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void teachersSaveAccessShouldBeDeniedForKlient() throws Exception {
        mockMvc.perform(
                post("/teachers").with(user("klient").password("klient"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().is(403));
    }

    @Test
    public void adminAccessToCourseDetailsShouldBeGranted() throws Exception {
        mockMvc.perform(get("/courses/{id}", 1).with(user("admin").roles("ADMIN").password("admin")))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
