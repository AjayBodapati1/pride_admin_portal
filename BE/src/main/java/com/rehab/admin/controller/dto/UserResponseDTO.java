package com.rehab.admin.controller.dto;

import java.time.LocalDate;

public record UserResponseDTO(
        Integer id,
        String name,
        LocalDate dateOfBirth,
        String fatherName,
        String mobileNumber,
        String email,
        LocalDate dateOfJoining,
        String password,
        String role,
        Boolean active
) {}
