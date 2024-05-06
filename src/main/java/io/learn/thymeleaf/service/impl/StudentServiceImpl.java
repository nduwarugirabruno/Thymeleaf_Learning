package io.learn.thymeleaf.service.impl;

import io.learn.thymeleaf.entity.Student;
import io.learn.thymeleaf.repository.StudentRepository;
import io.learn.thymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.learn.thymeleaf.utils.Global.invalidCreatedStudent;

@Service
public class StudentServiceImpl implements StudentService {

    final
    StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * @param student: data of student to create
     * @return status code 200 if success, 422 if invalid data, 500 if error
     */
    @Override
    public ResponseEntity<Student> create(Student student) {
        try {
            if (invalidCreatedStudent(student.getFirstName(), student.getLastName(), student.getEmail()))
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); // renvoi un code de statut 422

            student.setImmatriculation(generateImmatriculation());

            Student stmp = studentRepository.save(student);
            System.out.println("created -> " + stmp); // renvoie un code de statut 200
            return ResponseEntity.ok(stmp);
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    /**
     * @param student: new data of student to update
     * @param id:      of member to update
     * @return status code 200 if success, 422 if invalid data, 500 if error
     */
    @Override
    public ResponseEntity<Student> update(Student student, UUID id) {
        try {
            Optional<Student> uses = studentRepository.findById(id);
            if (uses.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // renvoie un code de statut 404

            if (student.getFirstName() != null && !uses.get().getFirstName().equals(student.getFirstName()))
                uses.get().setFirstName(student.getFirstName());

            if (student.getLastName() != null && !uses.get().getLastName().equals(student.getLastName()))
                uses.get().setLastName(student.getLastName());

            if (student.getEmail() != null && !uses.get().getEmail().equals(student.getEmail()))
                uses.get().setEmail(student.getEmail());

            Student mtmp = studentRepository.save(uses.get());
            System.out.println("updated -> " + mtmp);
            return ResponseEntity.ok(mtmp); // renvoie un code de statut 200
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    /**
     * @param id of student to delete
     * @return 200 if success, 404 if not found, 500 if error
     */
    @Override
    public ResponseEntity<String> delete(UUID id) {
        try {
            if (studentRepository.findById(id).isPresent()) {
                studentRepository.deleteById(id);
                return ResponseEntity.ok("Student deleted"); // renvoie un code de statut 200
            } else
                return new ResponseEntity<>("Student doesn't exist", HttpStatus.NOT_FOUND); // renvoie un code de statut 404
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    /**
     * @return a list of all student
     */
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    /**
     * @param id: of student to get
     * @return a student data if existed, null object if not
     */
    @Override
    public Student getById(UUID id) {
        if (studentRepository.findById(id).isPresent()) return studentRepository.findById(id).get();
        else return null;
    }

    /**
     * @param firstName: of member to get
     * @return a list of students with this firstName
     */
    @Override
    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    /**
     * @param lastName: of member to get
     * @return a list of students with this lastName
     */
    @Override
    public List<Student> getByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    /**
     * @param email of student to get
     * @return a list of students with this email
     */
    @Override
    public List<Student> getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    private String generateImmatriculation() {
        int lastTwoDigits = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return lastTwoDigits + (char) ('A' + (lastTwoDigits % 26)) + String.format("%4s", studentRepository.findAll().toArray().length).replace(' ', '0');
    }

}
