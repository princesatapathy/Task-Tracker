package com.amcvillan.Task.Tracker.service.impl;

import com.amcvillan.Task.Tracker.domain.entities.TaskList;
import com.amcvillan.Task.Tracker.repositories.TaskListRepository;
import com.amcvillan.Task.Tracker.service.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceimpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceimpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> ListTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList CreateTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("Task List Already has an ID!");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task List Title Must Be Non-Empty!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList UpdateTaskList(UUID taskListId, TaskList taskList) {
        if(null==taskList.getId()){
            throw new IllegalArgumentException("Task List Must Have An ID!");
        }
        if(!Objects.equals(taskList.getId(), taskListId)){
            throw new IllegalArgumentException("Task List ID mismatch!");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Task List Not Found!"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void DeleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
