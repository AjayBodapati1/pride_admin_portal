package com.rehab.admin.controller.mapper;

import com.rehab.admin.controller.dto.AttendanceDTO;
import com.rehab.admin.model.Attendance;
import com.rehab.admin.model.User;
import com.rehab.admin.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceDtoMapper {

    @Autowired
    private UserRepository userRepository;

    public Attendance toAttendanceEntity(AttendanceDTO dto) {
        if (dto.userId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User user = userRepository.findById(String.valueOf(dto.userId()))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new Attendance(user, dto.status());
    }

}
