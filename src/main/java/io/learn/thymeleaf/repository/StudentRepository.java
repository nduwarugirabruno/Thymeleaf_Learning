package io.learn.thymeleaf.repository;

import io.learn.thymeleaf.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("select s from Student s where s.firstName like %?1%")
    List<Student> findByFirstName(String firstName);

    @Query("select s from Student s where s.lastName like %?1%")
    List<Student> findByLastName(String lastName);

    @Query("select s from Student s where s.email like %?1%")
    List<Student> findByEmail(String email);
}
