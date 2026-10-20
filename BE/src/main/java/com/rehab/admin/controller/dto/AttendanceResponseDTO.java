package com.rehab.admin.controller.dto;

import com.rehab.admin.model.Attendance;
import java.time.LocalDate;

public record AttendanceResponseDTO(
        LocalDate date,
        Attendance.AttendanceStatus status
) {}
