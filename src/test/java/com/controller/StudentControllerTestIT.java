package com.controller;

import com.model.Student;
import com.model.StudentBuilder;
import com.service.StudentService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Michał on 2017-02-05.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTestIT {

    private static final String STUDENT_PATH = "/student/";
    private static final String ALL = "all";
    private static final String NEW = "new";
    private static final String SHOW_VIEW = "show";
    private static final String STUDENT_VIEW = "student";
    private static final String STUDENT_ATTRIBUTE = "student";
    private static final String STUDENTS_ATTRIBUTE = "students";
    private static final String FIRST_NAME_PROPERTY = "firstName";
    private static final String LAST_NAME_PROPERTY = "lastName";
    private static final String UNIVERSITY_NAME_PROPERTY = "universityName";
    private static final Matcher<Object> NULL_VALUE_MATCHER = Matchers.equalTo(null);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldReturnShowViewWithNoStudentWhenNoStudentInDb() throws Exception {
        givenStudentsInDb(emptyList());

        expectShowStudentsViewContaining(emptyList());
    }

    @Test
    public void shouldReturnShowViewWithAllStudentsExistingInDb() throws Exception {
        Student student1 = StudentBuilder.getStudentWith("firstName1", "lastName1", "university1");
        Student student2 = StudentBuilder.getStudentWith("firstName2", "lastName2", "university2");
        givenStudentsInDb(asList(student1, student2));

        expectShowStudentsViewContaining(asList(student1, student2));
    }

    @Test
    public void shouldReturnStudentCreateFormWIthNoStudentData() throws Exception {
        this.mvc.perform(get(STUDENT_PATH + NEW)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name(STUDENT_VIEW))
                .andExpect(model().attribute(STUDENT_ATTRIBUTE, hasProperty(FIRST_NAME_PROPERTY, NULL_VALUE_MATCHER)))
                .andExpect(model().attribute(STUDENT_ATTRIBUTE, hasProperty(LAST_NAME_PROPERTY, NULL_VALUE_MATCHER)))
                .andExpect(model().attribute(STUDENT_ATTRIBUTE, hasProperty(UNIVERSITY_NAME_PROPERTY, NULL_VALUE_MATCHER)));
    }

    @Test
    public void shouldCreateNewStudentAndReturnShowViewWIthThatStudent() throws Exception {
        String firstName = "firstName1";
        String lastName = "lastName1";
        String universityName = "university1";
        Student student1 = StudentBuilder.getStudentWith(firstName, lastName, universityName);
        givenStudentsInDb(singletonList(student1));

        this.mvc.perform(post(STUDENT_PATH + NEW)
                .param(FIRST_NAME_PROPERTY, firstName)
                .param(LAST_NAME_PROPERTY, lastName)
                .param(UNIVERSITY_NAME_PROPERTY, universityName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        expectShowStudentsViewContaining(singletonList(student1));
    }

    private ResultActions expectShowStudentsViewContaining(List<Student> students) throws Exception {
        return this.mvc.perform(get(STUDENT_PATH + ALL)
                .accept(MediaType.APPLICATION_JSON))
                       .andExpect(status().isOk())
                       .andExpect(view().name(SHOW_VIEW))
                       .andExpect(model().attribute(STUDENTS_ATTRIBUTE, students));
    }

    private void givenStudentsInDb(List<Student> students) {
        given(this.studentService.getAll())
                .willReturn(students);
    }
}