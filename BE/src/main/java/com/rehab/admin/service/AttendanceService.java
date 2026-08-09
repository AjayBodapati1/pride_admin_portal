package com.rehab.admin.service;


import com.rehab.admin.controller.dto.AttendanceDto;
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

    public void markAttendance(AttendanceDto dto) {
        LocalDate date = dto.getDate() != null ? dto.getDate() : LocalDate.now();

        if (attendanceRepository.existsByUserIdAndAttendanceDate(dto.getUserId(), date)) {
            throw new IllegalArgumentException("Attendance already marked for today.");
        }

        User user = userRepository.findById(String.valueOf(dto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setAttendanceDate(date);
        attendance.setStatus(Attendance.AttendanceStatus.valueOf(dto.getStatus().toString().toUpperCase()));

        attendanceRepository.save(attendance);
    }
}