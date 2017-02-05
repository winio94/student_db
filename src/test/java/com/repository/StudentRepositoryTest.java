package com.repository;

import com.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.model.StudentBuilder.getStudentWith;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Michał on 2017-02-05.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void getAllUsersShouldReturnEmptyList() throws Exception {
        List<Student> students = (List<Student>) studentRepository.findAll();

        assertEquals(0, students.size());
    }

    @Test
    public void getAllUsersShouldReturnTwoUsers() throws Exception {
        Student student1 = getStudentWith("firstName1", "lastName1", "university1");
        Student student2 = getStudentWith("firstName2", "lastName2", "university2");
        givenStudentsInDb(student1, student2);

        List<Student> students = (List<Student>) studentRepository.findAll();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

    @Test
    public void postUserShouldSaveUserInDb() throws Exception {
        Student student = studentRepository.save(getStudentWith("firstName1", "lastName1", "university1"));

        Student studentInDb = entityManager.find(Student.class, student.getId());
        assertEquals(studentInDb, student);
    }

    @Test(expected = Exception.class)
    public void postNullShouldThrowAnException() throws Exception {
        Student student = null;
        studentRepository.save(student);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenDeleteUserNotExistingInDb() throws Exception {
        Student student = getStudentWith("firstName1", "lastName1", "university1");

        studentRepository.delete(-1L);
        studentRepository.delete(student);
    }

    @Test
    public void shouldDeleteUserFromDb() throws Exception {
        Student student1 = studentRepository.save(getStudentWith("firstName1", "lastName1", "university1"));
        Student student2 = studentRepository.save(getStudentWith("firstName2", "lastName2", "university2"));
        givenStudentsInDb(student1, student2);

        studentRepository.delete(student1.getId());
        studentRepository.delete(student2);

        List<Student> students = (List<Student>) studentRepository.findAll();
        assertEquals(0, students.size());
    }

    private void givenStudentsInDb(Student... students) {
        asList(students).forEach(student -> this.entityManager.persist(student));
    }
}