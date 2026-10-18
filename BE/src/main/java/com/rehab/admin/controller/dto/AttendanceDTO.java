package com.rehab.admin.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rehab.admin.model.Attendance;


public record AttendanceDTO(
        @JsonProperty("id") Long userId,
        Attendance.AttendanceStatus status
) {}