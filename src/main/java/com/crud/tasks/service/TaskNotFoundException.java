package com.crud.tasks.service;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(final Long id) {
        super("Task #" + id + " not found!");
    }
}
