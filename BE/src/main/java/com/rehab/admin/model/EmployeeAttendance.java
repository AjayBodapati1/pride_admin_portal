package com.rehab.admin.model;

import lombok.*;
import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttendance {
    LocalDate date;
    String status;
}
