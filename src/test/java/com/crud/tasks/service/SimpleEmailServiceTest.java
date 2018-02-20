package com.crud.tasks.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.mail.EmailCreator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    EmailCreator emailCreator;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", "");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //When
        simpleEmailService.send(mail, emailCreator);
        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }
    @Test
    public void shouldSendEmailWithCC() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", "test2@test.com");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());
        //When
        simpleEmailService.send(mail, emailCreator);
        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }
}