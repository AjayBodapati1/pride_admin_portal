package com.rehab.admin.repository;

import com.rehab.admin.model.Attendance;
import com.rehab.admin.model.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByUserIdAndAttendanceDate(Long userId, LocalDate date);

    @Query(value = " SELECT a.attendance_date, a.status FROM attendance a" +
            " WHERE a.user_id = ?1 AND a.attendance_date BETWEEN ?2 AND ?3" +
            " ORDER BY attendance_date ASC", nativeQuery = true)
    List<EmployeeAttendance> getEmployeeAttendance(Long userId, LocalDate fromDate, LocalDate toDate);
}