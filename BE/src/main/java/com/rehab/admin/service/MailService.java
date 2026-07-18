package com.rehab.admin.service;

import com.rehab.admin.model.MailRequest;
import com.rehab.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmail(MailRequest mailRequest) {
        if(mailRequest.getRole().equals("student")){
            return sendEmailToStudents(mailRequest.getSub(), mailRequest.getMsg());
        } else if(mailRequest.getRole().equals("employee")){
            return sendEmailToEmployees(mailRequest.getSub(), mailRequest.getMsg());
        } else {
            return "Invalid role";
        }
    }

    private String sendEmailToStudents(String sub, String msg) {
        try {

            SimpleMailMessage mailMessage =new SimpleMailMessage();
            String body = msg;
            mailMessage.setFrom(sender);
            mailMessage.setSubject(sub);

            List<User> users = userService.getActiveStudents();
            for (User user : users) {
                body = "Hello "+user.getFatherName()+",\n\n"+ body;
                mailMessage.setText(body);
                mailMessage.setTo(user.getEmail());
                javaMailSender.send(mailMessage);
            }
            return "Mails to students sent successfully";

        } catch (Exception e) {
            return "Error while sending mail";
        }
    }

    private String sendEmailToEmployees(String sub, String msg) {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            String body = msg;
            mailMessage.setFrom(sender);
            mailMessage.setSubject(sub);

            List<User> users = userService.getActiveEmployees();
            for (User user : users) {
                body = "Hello " + user.getName() + ",\n\n" + body;
                mailMessage.setText(body);
                mailMessage.setTo(user.getEmail());
                javaMailSender.send(mailMessage);
            }
            return "Mails to employees sent successfully";

        } catch (Exception e) {
            return "Error while sending mail";
        }
    }


}
