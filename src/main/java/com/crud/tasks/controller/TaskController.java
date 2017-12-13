package com.crud.tasks.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{taskId}")
    public TaskDto getTask(@PathVariable final Long taskId) {
        return taskMapper.mapToTaskDto(service.getTaskById(taskId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{taskId}")
    public void deleteTask(@PathVariable final Long taskId) {
        service.deleteTask(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{taskId}", consumes = APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody final TaskDto taskDto, @PathVariable final Long taskId) {
        taskDto.setId(taskId);
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody final TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
