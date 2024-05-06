package io.learn.thymeleaf.controller;

import io.learn.thymeleaf.entity.Student;
import io.learn.thymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class StudentController {

    final
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students")
    public String getAll(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students_home";
    }

    @GetMapping(path = "/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping(path = "/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.create(student);
        return "redirect:students";
    }

    @GetMapping(path = "/students/update/{id}")
    public String updateStudentForm(Model model, @PathVariable UUID id) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "update_student";
    }

    @PutMapping(path = "/students")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.update(student, student.getId());
        return "redirect:students";
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Student> update(@RequestBody Student student, @PathVariable UUID id) {
        return studentService.update(student, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        return studentService.delete(id);
    }

    @GetMapping(path = "/read/id/{id}")
    public Student getById(@PathVariable UUID id) {
        return studentService.getById(id);
    }

    @GetMapping(path = "/read/firstname/{firstName}")
    public List<Student> getAllByFirstName(@PathVariable String firstName) {
        return studentService.getByFirstName(firstName);
    }

    @GetMapping(path = "/read/lastname/{lastName}")
    public List<Student> getAllBySurName(@PathVariable String lastName) {
        return studentService.getByLastName(lastName);
    }

    @GetMapping(path = "/read/email/{email}")
    public List<Student> getAllByEmail(@PathVariable String email) {
        return studentService.getByEmail(email);
    }


}
