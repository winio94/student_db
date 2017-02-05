package com.controller;

import com.model.Student;
import com.service.StudentService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Inject
    private StudentService studentService;

    @PostMapping(value = "/new")
    public ModelAndView save(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("student", modelMapWithStudent(student));
        }
        studentService.save(student);
        return showViewWithAllStudents();
    }

    @GetMapping(value = "/new")
    public ModelAndView getStudentForm() {
        return new ModelAndView("student", modelMapWithNewStudent());
    }

    @GetMapping(value = "/all")
    public ModelAndView showStudents(HttpServletRequest request) {
        return new ModelAndView("show", modelMapWithAllStudents());
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStudent(@PathVariable Long id) throws NotFoundException {
        return new ModelAndView("student", modelMapWithOneStudent(id));
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable("id") Long id) {
        studentService.remove(id);
        return showViewWithAllStudents();
    }

    private ModelAndView showViewWithAllStudents() {
        ModelAndView showView = new ModelAndView(new RedirectView("/student/all"));
        showView.addObject(modelMapWithAllStudents());
        return showView;
    }

    private ModelMap modelMapWithNewStudent() {
        return modelMapWithStudent(new Student());
    }

    private ModelMap modelMapWithOneStudent(Long studentId) throws NotFoundException {
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