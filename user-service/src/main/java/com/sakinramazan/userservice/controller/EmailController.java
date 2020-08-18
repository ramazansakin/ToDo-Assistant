package com.sakinramazan.userservice.controller;

import com.sakinramazan.userservice.model.EmailSentRequest;
import com.sakinramazan.userservice.model.EmailSentWithAttachmentRequest;
import com.sakinramazan.userservice.service.EmailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/email")
@Api(value = "EmailController")
public class EmailController {

    private final EmailService emailService;

    // You can refactor here to get all logic to EmailService for best practise :)
    @PostMapping("/send-email")
    public void sendEmail(@RequestBody @Valid EmailSentRequest request) {
        emailService.sendEmail(request);
    }

    @PostMapping("/send-email-with-attachment")
    public void sendEmailWithAttachment(@RequestBody @Valid EmailSentWithAttachmentRequest request){
        emailService.sendEmailWithAttachment(request);
    }
}
