package com.crud.tasks.controller;

import java.util.ArrayList;
import java.util.List;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{taskId}")
    public TaskDto getTask(String taskId) {
        return new TaskDto ((long)1, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{taskId}")
    public void deleteTask(String taskId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "{taskId}")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long)1, "Edited test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createTask(TaskDto taskDto) {

    }
}
