package com.crud.tasks.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        assertEquals(1, taskList.size());
        assertEquals("test_task", taskList.get(0).getTitle());
    }
    @Test
    public void shouldGetTaskById() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");
        when(taskRepository.findById(anyLong())).thenReturn(Optional.ofNullable(task));
        //When
        Task resultTask = dbService.getTaskById(task.getId());
        //Then
        assertEquals(1L, (long)resultTask.getId());
        assertEquals("test_task", resultTask.getTitle());
        assertEquals("test_content", resultTask.getContent());
    }
    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task resultTask = dbService.saveTask(task);
        //Then
        assertEquals(1L, (long)resultTask.getId());
        assertEquals("test_task", resultTask.getTitle());
    }
    @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        doNothing().when(taskRepository).deleteById(anyLong());
        //When
        dbService.deleteTask(task.getId());
        //Then
        verify(taskRepository,times(1)).deleteById(task.getId());
    }
}