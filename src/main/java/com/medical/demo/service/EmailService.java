package com.medical.demo.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.medical.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private final UserService userService;

    public void sendSimpleEmail(String username, MultipartFile attachment) throws MessagingException {

        User u = userService.findByUsername(username);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("tudorcampan@gmail.com");
        helper.setTo(u.getEmail());
        helper.setSubject("Information on the patient");
        helper.setText("Here is the information you requested on the patient." +
                "\nBest regards!");

        helper.addAttachment(attachment.getOriginalFilename(), attachment);
        mailSender.send(mimeMessage);
        System.out.println("Email sent");
    }
}
