package com.rehab.admin.configuration;

import com.rehab.admin.execptions.AttendanceMarkedException;
import com.rehab.admin.execptions.EmailUserNotFoundException;
import com.rehab.admin.execptions.LogoNotFoundException;
import com.rehab.admin.execptions.SendMailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExcptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AttendanceMarkedException.class)
    public ProblemDetail handleAttendanceMarkedException(AttendanceMarkedException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SendMailException.class)
    public ProblemDetail handleSendmailException(SendMailException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LogoNotFoundException.class)
    public ProblemDetail handleLogoNotFoundException(LogoNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailUserNotFoundException.class)
    public ProblemDetail handleEmailUserNotFoundException(EmailUserNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

}
