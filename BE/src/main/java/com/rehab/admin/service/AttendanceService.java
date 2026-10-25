package com.rehab.admin.service;

import com.rehab.admin.execptions.AttendanceMarkedException;
import com.rehab.admin.model.Attendance;
import com.rehab.admin.model.EmployeeAttendance;
import com.rehab.admin.repository.AttendanceRepository;
import com.rehab.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AttendanceService {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(UserRepository userRepository, AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public void markAttendance(Attendance attendance) {
        LocalDate date = attendance.getAttendanceDate() != null ? attendance.getAttendanceDate() : LocalDate.now();

        if (attendanceRepository.existsByUserIdAndAttendanceDate(Long.valueOf(attendance.getUser().getId()), date)) {
            throw new AttendanceMarkedException( attendance.getUser().getName() +  "'s attendance already marked for today.");
        }

        attendance.setAttendanceDate(date);

        attendanceRepository.save(attendance);
        log.info("marked attendance for {}", attendance.getUser().getName());
    }

    public List<EmployeeAttendance> getAttendance(Long userId, LocalDate fromDate, LocalDate toDate) {
        log.info("listing attendance for {}",userId);
        return attendanceRepository.getEmployeeAttendance(userId, fromDate, toDate);
    }
}