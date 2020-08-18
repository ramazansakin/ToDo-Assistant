package com.sakinramazan.userservice.scheduler;

import com.sakinramazan.userservice.model.EmailSentRequest;
import com.sakinramazan.userservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailSendingScheduler {

    private final EmailService emailService;

    @Scheduled(cron = "0 0/3 * * * *")
    public void vulnerabilityGroupCodeAssign() {

        try {
            EmailSentRequest request = new EmailSentRequest();
            request.setMailUser("ramazansakin63@gmail.com");
            request.setEmailHeadline("Sample Headline");
            request.setMailDetails("Sample Details");
            emailService.sendEmail(request);
        } catch (Exception e) {
            log.error(e.toString());
        }

    }

}
