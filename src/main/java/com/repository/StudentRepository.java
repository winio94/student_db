package com.repository;

import com.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michał on 2016-11-28.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}