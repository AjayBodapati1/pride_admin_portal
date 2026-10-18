package com.rehab.admin.controller.dto;

public record MailRequestDTO(
        String sub,
        String msg,
        String role
) {}