package io.learn.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "immatriculation", length = 7, nullable = false, updatable = false, unique = true)
    private String immatriculation;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_telephone")
    private String numeroTelephone;

    public Student(String immatriculation, String firstName, String lastName, String email, String numeroTelephone) {
        this.immatriculation = immatriculation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
    }

    @Override
    public String toString() {
        return "\tStudent ->" +
                "\n\t\tid: " + id +
                "\n\t\timmatriculation: " + immatriculation +
                "\n\t\tfirstName: " + firstName +
                "\n\t\tlastName: " + lastName +
                "\n\t\temail: " + email +
                "\n\t\tnumeroTelephone: " + numeroTelephone;
    }
}
