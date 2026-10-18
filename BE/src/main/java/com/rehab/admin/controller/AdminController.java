package com.rehab.admin.controller;

import com.rehab.admin.controller.dto.*;
import com.rehab.admin.controller.mapper.AttendanceDtoMapper;
import com.rehab.admin.controller.mapper.MailDtoMapper;
import com.rehab.admin.controller.mapper.UserDtoMapper;
import com.rehab.admin.model.MailRequest;
import com.rehab.admin.model.PdfRequest;
import com.rehab.admin.model.User;
import com.rehab.admin.service.AttendanceService;
import com.rehab.admin.service.MailService;
import com.rehab.admin.service.PdfService;
import com.rehab.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceDtoMapper attendanceDtoMapper;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @Autowired
    private MailDtoMapper mailDtoMapper;

    @GetMapping("/getStudents")
    public ResponseEntity<List<StudentResponseDTO>> getStudents(){
        List<User> users = userService.getStudents();
        return new ResponseEntity<>(userDtoMapper.toStudentDtoList(users), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(userDtoMapper.toUserDtoList(users), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody NewUserRequestDTO user) {
        User newUser = userDtoMapper.toUserEntity(user);
        System.out.println(newUser.toString());
        User savedUser = userService.createUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/editUser/{email}")
    public ResponseEntity<UserResponseDTO> editUser(@PathVariable String email,@RequestBody EditUserRequestDTO editUserRequestDTO) {
        User user = userDtoMapper.toEditUserEntity(editUserRequestDTO);
        User editedUser = userService.editUser(email, user);
        return new ResponseEntity<>(userDtoMapper.toUserDto(editedUser), HttpStatus.CREATED);
    }

    @GetMapping("/getEmployees")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        List<User> users = userService.getEmployees();
        return new ResponseEntity<>(userDtoMapper.toEmployeeDtoList(users), HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody MailRequestDTO mailRequestDTO){
        MailRequest mailRequest = mailDtoMapper.toMailEntity(mailRequestDTO);
        return new ResponseEntity<>(mailService.sendEmail(mailRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generate(@RequestBody PdfRequest request) throws Exception {

        byte[] pdf = pdfService.generatePdf(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/attendance")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceDTO dto) {
        try {
            attendanceService.markAttendance(attendanceDtoMapper.toAttendanceEntity(dto));
            return ResponseEntity.ok("Attendance recorded successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}