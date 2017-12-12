package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Optional<Task> findById(Long id);
}
