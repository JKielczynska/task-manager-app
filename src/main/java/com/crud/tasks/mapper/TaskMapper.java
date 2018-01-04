package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    /**Map from TaskDto to Task.*/
    public Task mapToTask(final TaskDto taskDto) {
        return new Task(
          taskDto.getId(),
          taskDto.getTitle(),
          taskDto.getContent());
    }

    /**Map from Task to TaskDto.*/
    public TaskDto mapToTaskDto(final Task task) {
        return new TaskDto(task.getId(),
                task.getTitle(),
                task.getContent());
    }

    /**Map from Task list to TaskDto list.*/
    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(t -> new TaskDto(t.getId(), t.getTitle(), t.getContent()))
                .collect(Collectors.toList());

    }
}
