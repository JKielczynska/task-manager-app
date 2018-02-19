package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.mail.DailyMailCreatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private DailyMailCreatorService dailyMailCreatorService;

    private static final String SUBJECT = "Task Application";

    /**Sending a scheduled e-mail with information about the number of tasks in the database.*/
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you have " + size + " task" + pluralize(size),
                ""), dailyMailCreatorService);
    }

    private String pluralize(final long count) {
        return count == 1 ? "." : "s.";
    }
}
