package com.service;

import com.model.Student;

import java.util.List;

/**
 * Created by Michał on 2016-11-28.
 */
public interface StudentService {
    Student save(Student student);

    List<Student> getAll();

    void remove(Student student);

    void remove(Long id);

    Student findOne(Long id);
}