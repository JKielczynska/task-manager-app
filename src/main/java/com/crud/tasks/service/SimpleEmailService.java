package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.mail.EmailCreator;
import com.crud.tasks.service.mail.MailCreatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailCreatorService mailCreatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    /**Method allows to send emails from the application.*/
    public void send(final Mail mail, final EmailCreator emailCreator) {
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail, emailCreator));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, final EmailCreator emailCreator) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(emailCreator.buildEmailMessage(mail.getMessage()), true);
        };
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCc().isEmpty()) {
            LOGGER.info("CC is not included.");
        } else {
            mailMessage.setCc(mail.getToCc());
            LOGGER.info("CC added.");
        }
        return mailMessage;
    }
}
