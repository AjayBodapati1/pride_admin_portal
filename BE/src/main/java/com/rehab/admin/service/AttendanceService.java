package com.rehab.admin.service;

import com.rehab.admin.model.Attendance;
import com.rehab.admin.model.User;
import com.rehab.admin.repository.AttendanceRepository;
import com.rehab.admin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(UserRepository userRepository, AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void markAttendance(Attendance attendance) {
        LocalDate date = attendance.getAttendanceDate() != null ? attendance.getAttendanceDate() : LocalDate.now();

        if (attendanceRepository.existsByUserIdAndAttendanceDate(Long.valueOf(attendance.getUser().getId()), date)) {
            throw new IllegalArgumentException("Attendance already marked for today.");
        }

        attendance.setAttendanceDate(date);

        attendanceRepository.save(attendance);
    }
}