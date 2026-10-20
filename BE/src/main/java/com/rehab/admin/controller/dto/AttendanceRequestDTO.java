package com.rehab.admin.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record AttendanceRequestDTO(
        @JsonProperty("id") Long userId,
        LocalDate fromDate,
        LocalDate toDate
) {}
