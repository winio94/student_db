package com.service;

import com.model.Student;
import com.repository.StudentRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
        studentRepository.delete(id);
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findOne(id);
    }
}