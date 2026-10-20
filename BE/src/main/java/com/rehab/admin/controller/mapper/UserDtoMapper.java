package com.rehab.admin.controller.mapper;

import com.rehab.admin.controller.dto.*;
import com.rehab.admin.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserDtoMapper {

    public User toUserEntity(NewUserRequestDTO userRequestDTO){
        User newUser = new User(
                userRequestDTO.name(),
                userRequestDTO.dateOfBirth(),
                userRequestDTO.fatherName(),
                userRequestDTO.mobileNumber(),
                userRequestDTO.email(),
                userRequestDTO.dateOfJoining(),
                userRequestDTO.role(),
                userRequestDTO.active()
        );
        return newUser;
    }

    public User toEditUserEntity(EditUserRequestDTO userRequestDTO){
        User newUser = new User(
                userRequestDTO.name(),
                userRequestDTO.dateOfBirth(),
                userRequestDTO.fatherName(),
                userRequestDTO.mobileNumber(),
                userRequestDTO.email(),
                userRequestDTO.dateOfJoining(),
                userRequestDTO.password(),
                userRequestDTO.role(),
                userRequestDTO.active()
        );
        return newUser;
    }

    public UserResponseDTO toUserDto(User user){

        UserResponseDTO responseDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getDateOfBirth(),
                user.getFatherName(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getDateOfJoining(),
                user.getRole(),
                user.getActive()
        );
        return responseDTO;
    }

    public StudentResponseDTO toStudentDto(User user){

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                user.getId(),
                user.getName(),
                user.getDateOfBirth(),
                user.getFatherName(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getDateOfJoining(),
                user.getActive()
        );
        return responseDTO;
    }

    public EmployeeResponseDTO toEmployeeDto(User user){

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        return responseDTO;
    }

    public List<UserResponseDTO> toUserDtoList(List<User> users){
        if(users == null){
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::toUserDto)
                .toList();
    }

    public List<StudentResponseDTO> toStudentDtoList(List<User> users){
        if(users == null){
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::toStudentDto)
                .toList();
    }

    public List<EmployeeResponseDTO> toEmployeeDtoList(List<User> users){
        if(users == null){
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::toEmployeeDto)
                .toList();
    }
}
