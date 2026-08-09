package com.rehab.admin.controller.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceDto {
    private Long userId;
    private LocalDate date;
    private String status;
}