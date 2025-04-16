package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
    }

    public Task createTask(TaskDTO dto) {
        Task task = new Task(nextId++, dto.getTitle(), dto.getDescription());
        tasks.add(task);
        return task;
    }

    public Task updateTask(int id, TaskDTO dto) {
        Task task = getTaskById(id);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        return task;
    }

    public void deleteTask(int id) {
        Task task = getTaskById(id);
        tasks.remove(task);
    }
}
