package com.sakinramazan.userservice.service.impl;

import com.sakinramazan.userservice.model.EmailSentRequest;
import com.sakinramazan.userservice.model.EmailSentWithAttachmentRequest;
import com.sakinramazan.userservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailSentRequest request) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(request.getMailUser());
        msg.setSubject(request.getEmailHeadline());
        msg.setText(request.getMailDetails());

        javaMailSender.send(msg);
    }

    @Override
    public void sendEmailWithAttachment(EmailSentWithAttachmentRequest request) {

        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(request.getMailUser());
            helper.setSubject(request.getEmailHeadline());
            // true = text/html
            helper.setText(request.getMailDetails(), true);

            // hard coded a file path
            //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
            if (request.getAttachmentName() == null)
                request.setAttachmentName("default_attachment_name_here");
            helper.addAttachment(request.getAttachmentName(), new ClassPathResource(request.getAttachmentPath()));

            javaMailSender.send(msg);
        } catch (Exception ex) {
            log.error(ex.toString());
        }

    }
}
