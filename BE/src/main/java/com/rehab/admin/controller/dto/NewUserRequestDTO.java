package com.rehab.admin.controller.dto;

import java.time.LocalDate;

public record NewUserRequestDTO(
        String name,
        LocalDate dateOfBirth,
        String fatherName,
        String mobileNumber,
        String email,
        LocalDate dateOfJoining,
        String role,
        Boolean active
) {}
