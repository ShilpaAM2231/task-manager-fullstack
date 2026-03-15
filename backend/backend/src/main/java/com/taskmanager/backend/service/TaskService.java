package com.taskmanager.backend.service;

import java.util.List;

import com.taskmanager.backend.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.backend.entity.Task;
import com.taskmanager.backend.repository.TaskRepository;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository repository;

    // Get all tasks
    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks");
        return repository.findAll();
    }

    // Save task
    public Task saveTask(Task task) {
        logger.info("Saving new task: {}", task.getTitle());
        return repository.save(task);
    }

    // Delete task
    public void deleteTask(Long id) {
        logger.info("Deleting task with id: {}", id);
        repository.deleteById(id);
    }

    // Update task
    public Task updateTask(Long id, Task taskDetails) {

        logger.info("Updating task with id: {}", id);

        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return repository.save(task);
    }
}