package com.rehab.admin.controller.dto;

public record ChangePasswordRequestDTO(
        String email,
        String newPassword
) {}
