package com.rehab.admin.controller.dto;

import java.time.LocalDate;

public record StudentResponseDTO(
        Integer id,
        String name,
        LocalDate dateOfBirth,
        String fatherName,
        String mobileNumber,
        String email,
        LocalDate dateOfJoining,
        Boolean active
) {}