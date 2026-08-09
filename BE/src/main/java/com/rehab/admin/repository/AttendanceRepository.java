package com.rehab.admin.repository;

import com.rehab.admin.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByUserIdAndAttendanceDate(Long userId, LocalDate date);
}