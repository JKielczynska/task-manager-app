package com.crud.tasks.service.mail;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class DailyMailCreatorService implements EmailCreator {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildEmailMessage(final String message) {

        Context context = new Context();
        context.setVariable("preview", "Daily information about the number of tasks.");
        context.setVariable("task_count", taskRepository.count());
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("daily-mail", context);
    }
}
