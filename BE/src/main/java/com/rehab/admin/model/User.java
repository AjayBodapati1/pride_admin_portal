package com.rehab.admin.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "father_name", nullable = false)
    private String fatherName;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "date_of_joining", nullable = false)
    private LocalDate dateOfJoining;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean active;

    public User(String name, LocalDate dateOfBirth, String fatherName, String mobileNumber, String email, LocalDate dateOfJoining, String role, Boolean active) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.fatherName = fatherName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.role = role;
        this.active = active;
    }

    public User(String name, LocalDate dateOfBirth, String fatherName, String mobileNumber, String email, LocalDate dateOfJoining, String password, String role, Boolean active) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.fatherName = fatherName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
        this.password = password;
        this.role = role;
        this.active = active;
    }
}