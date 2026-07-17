package com.rehab.admin.repository;

import com.rehab.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE role='student'", nativeQuery = true)
    List<User> getStudents();

    @Query(value = "SELECT * FROM users WHERE role='employee'", nativeQuery = true)
    List<User> getEmployees();

}