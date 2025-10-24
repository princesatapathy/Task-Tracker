package com.amcvillan.Task.Tracker.service;

import com.amcvillan.Task.Tracker.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> ListTaskLists();
    TaskList CreateTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList UpdateTaskList(UUID taskListId, TaskList taskList);
    void DeleteTaskList(UUID taskListId);
}
