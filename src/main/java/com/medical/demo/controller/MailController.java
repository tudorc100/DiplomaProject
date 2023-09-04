package com.medical.demo.controller;
import com.medical.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static com.medical.demo.UrlMapping.EMAIL;


    @CrossOrigin
    @RestController
    @RequestMapping(EMAIL)
    @RequiredArgsConstructor
    public class MailController {

        private final EmailService emailService;
        @PostMapping
        public ResponseEntity<String> sendEmail(@RequestParam("document") MultipartFile document, @RequestParam("username") String username) {
            try {
                emailService.sendSimpleEmail(username, document);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
            }

            return ResponseEntity.ok("Received username: " + username + " and document with size: " + document.getSize() + " bytes");
        }
    }

