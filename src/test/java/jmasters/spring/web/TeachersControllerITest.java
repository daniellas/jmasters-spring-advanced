package jmasters.spring.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import jmasters.spring.config.ServiceConfig;
import jmasters.spring.entity.Teacher;
import jmasters.spring.repository.TeacherRepository;
import jmasters.spring.web.config.MvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { JpaConfig.class, RepositoryConfig.class, ServiceConfig.class, MvcConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext
public class TeachersControllerITest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webCtx;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TeacherRepository teacherRepo;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
    }

    @Test
    public void test0TeacherListShouldBeEmpty() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void test1TeacherShouldBeSaved() throws JsonProcessingException, Exception {
        Teacher teacher = new Teacher();

        teacher.setFirstName("Jan");
        teacher.setLastName("Kowalski");

        mockMvc.perform(
                post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(teacher)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test2TeacherListShouldNotBeEmpty() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(hasSize(0)))));
    }

    @Test
    public void test3TeacherShouldBeUpdated() throws JsonProcessingException, Exception {
        Teacher teacher = teacherRepo.findAll().iterator().next();

        teacher.setNick("Jasio");

        mockMvc.perform(
                post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(teacher)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nick", is("Jasio")));
    }

    @Test
    public void test4TeacherShouldBeRemoved() throws JsonProcessingException, Exception {
        Teacher teacher = teacherRepo.findAll().iterator().next();

        mockMvc.perform(
                delete("/teachers/{id}", teacher.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test5TeacherListShouldBeEmpty() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
