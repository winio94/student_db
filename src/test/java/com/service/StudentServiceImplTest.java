package com.service;

import com.model.Student;
import com.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Michał on 2017-02-05.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void shouldSaveGivenStudent() throws Exception {
        Student student = mock(Student.class);

        studentService.save(student);

        verify(studentRepository).save(student);
    }

    @Test
    public void shouldReturnAllStudents() throws Exception {
        studentService.getAll();

        verify(studentRepository).findAll();
    }

    @Test
    public void shouldFindOneStudentWithGivenId() throws Exception {
        Long studentId = 1L;
        Student expectedStudent = new Student();
        when(studentRepository.findOne(studentId)).thenReturn(expectedStudent);

        Student foundStudent = studentService.findOne(studentId);

        assertEquals(expectedStudent, foundStudent);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenCouldNotFindStudentWithGivenId() throws Exception {
        studentService.findOne(-1L);
    }

    @Test
    public void shouldRemoveGivenStudent() throws Exception {
        Student student = new Student();
        studentService.remove(student);

        verify(studentRepository).delete(student);
    }

    @Test
    public void shouldRemoveStudentWithGivenId() throws Exception {
        Long studentID = 1L;
        studentService.remove(studentID);

        verify(studentRepository).delete(studentID);
    }
}