package com.controller;

import com.model.Student;
import com.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Inject
    private StudentService studentService;

    @PostMapping
    public ModelAndView save(Student student) {
        studentService.save(student);
        return new ModelAndView("show", modelMapWithAllStudents());
    }

    @GetMapping(value = "/new")
    public ModelAndView createStudent() {
        return new ModelAndView("/student", modelMapWithNewStudent());
    }

    @GetMapping
    public String showStudents(HttpServletRequest request) {
        request.setAttribute("students", getAllStudents());
        return "show";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStudent(@PathVariable Long id) {
        return new ModelAndView("student", modelMapWithOneStudent(id));
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, HttpServletRequest request) {
        studentService.remove(id);
        request.setAttribute("students", getAllStudents());
        return "show";
    }

    private ModelMap modelMapWithNewStudent() {
        return modelMapWithStudent(new Student());
    }

    private ModelMap modelMapWithOneStudent(Long studentId) {
        return modelMapWithStudent(studentService.findOne(studentId));
    }

    private ModelMap modelMapWithStudent(Student student) {
        ModelMap map = new ModelMap();
        map.put("student", student);
        return map;
    }

    private ModelMap modelMapWithAllStudents() {
        List<Student> allStudents = getAllStudents();
        ModelMap map = new ModelMap();
        map.put("students", allStudents);
        return map;
    }

    private List<Student> getAllStudents() {
        return studentService.getAll();
    }
}