package com.crud.tasks.mapper;

import static org.junit.Assert.assertEquals;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task title", "test task content");
        //When
        Task resultTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), resultTask.getId());
        assertEquals(taskDto.getTitle(), resultTask.getTitle());
        assertEquals(taskDto.getContent(), resultTask.getContent());
    }
    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test task title", "test task content");
        //When
        TaskDto resultTask = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), resultTask.getId());
        assertEquals(task.getTitle(), resultTask.getTitle());
        assertEquals(task.getContent(), resultTask.getContent());
    }
    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "test #1 task title", "test task content");
        Task task2 = new Task(2L, "test #2 task title", "test task content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        //When
        List<TaskDto> resultList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(taskList.size(), resultList.size());
        assertEquals(taskList.get(0).getTitle(), resultList.get(0).getTitle());
    }

}
