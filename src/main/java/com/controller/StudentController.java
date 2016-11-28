package com.controller;

import com.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/student")
@Controller
public class StudentController {
    private Map<Integer, Student> studentsMap = new HashMap<>();
    private static int id = 0;

    @PostMapping
    public ModelAndView save(Student student) {

        if (student.getId() == -1) {
            id++;
            student.setId(id);
            studentsMap.put(id, student);
        } else {
            studentsMap.put(student.getId(), student);
        }

        Iterator iter = studentsMap.keySet().iterator();
        List<Student> newMap = new ArrayList<>();

        while (iter.hasNext()) {
            Object key = iter.next();
            if (key != null) {
                newMap.add(studentsMap.get(key));
            }
        }

        ModelMap map = new ModelMap();
        map.put("students", newMap);

        return new ModelAndView("show", map);
    }

    @GetMapping(value = "/new")
    public ModelAndView createStudent() {
        Student form = new Student();
        ModelMap map = new ModelMap();
        map.put("student", form);
        return new ModelAndView("/student", map);
    }

    @GetMapping
    public String showStudents(HttpServletRequest request) {
        Iterator iterator = studentsMap.keySet().iterator();
        List<Student> newMap = new ArrayList<>();

        while (iterator.hasNext()) {
            Object key = iterator.next();
            if (key != null) {
                newMap.add(studentsMap.get(key));
            }
        }

        request.setAttribute("students", newMap);
        return "show";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStudent(@PathVariable String id) {
        ModelMap map = new ModelMap();
        map.put("student", studentsMap.get(Integer.parseInt(id)));

        return new ModelAndView("student", map);
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable String id, HttpServletRequest request) {
        studentsMap.remove(Integer.parseInt(id));

        Iterator iterator = studentsMap.keySet().iterator();
        List<Student> newMap = new ArrayList<>();

        while (iterator.hasNext()) {
            Object key = iterator.next();
            if (key != null) {
                newMap.add(studentsMap.get(key));
            }
        }
        request.setAttribute("students", newMap);
        return "show";
    }
}