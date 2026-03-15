package com.taskmanager.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taskmanager.backend.dto.TaskDTO;
import com.taskmanager.backend.entity.Task;
import com.taskmanager.backend.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService service;

    // GET all tasks
    @GetMapping
    public List<TaskDTO> getTasks() {

        List<Task> tasks = service.getAllTasks();

        return tasks.stream().map(task -> {
            TaskDTO dto = new TaskDTO();
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    // CREATE task
    @PostMapping
    public Task createTask(@Valid @RequestBody TaskDTO taskDTO) {

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        return service.saveTask(task);
    }

    // DELETE task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }

    // UPDATE task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        return service.updateTask(id, task);
    }
}