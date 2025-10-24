package com.amcvillan.Task.Tracker.service;

import com.amcvillan.Task.Tracker.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> ListTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
    Task updateTask(UUID taskListId, UUID id, Task task);
    void deleteTask(UUID taskListId, UUID taskId);
}
