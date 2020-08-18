package com.sakinramazan.userservice.service;

import com.sakinramazan.userservice.model.EmailSentRequest;
import com.sakinramazan.userservice.model.EmailSentWithAttachmentRequest;

public interface EmailService {

    void sendEmail(EmailSentRequest request);

    void sendEmailWithAttachment(EmailSentWithAttachmentRequest request);

}
