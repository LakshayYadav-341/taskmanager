package com.example.taskmanager;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        return removed ? "Task deleted" : "Task not found";
    }
}
