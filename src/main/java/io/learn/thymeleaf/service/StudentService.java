package io.learn.thymeleaf.service;

import io.learn.thymeleaf.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    ResponseEntity<Student> create(Student student);
    ResponseEntity<Student> update(Student student, UUID id);
    ResponseEntity<String> delete(UUID id);
    List<Student> getAll();
    Student getById(UUID id);
    List<Student> getByFirstName(String firstName);
    List<Student> getByLastName(String lastName);
    List<Student> getByEmail(String email);

}
