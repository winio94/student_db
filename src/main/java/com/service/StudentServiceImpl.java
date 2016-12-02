package com.service;

import com.model.Student;
import com.repository.StudentRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

/**
 * Created by Michał on 2016-11-28.
 */
@Named
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public void remove(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public void remove(Long id) {
        try {
            studentRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(errorMessageFor(id));
        }
    }

    @Override
    public Student findOne(Long id) {
        Student student = studentRepository.findOne(id);
        if (Objects.isNull(student)) {
            throw new RuntimeException(errorMessageFor(id));
        }
        return student;
    }

    private String errorMessageFor(Long id) {
        return String.format("Student with id = %s does not exist.", id);
    }
}