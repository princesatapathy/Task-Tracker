package com.amcvillan.Task.Tracker.service.impl;

import com.amcvillan.Task.Tracker.domain.entities.Task;
import com.amcvillan.Task.Tracker.domain.entities.TaskList;
import com.amcvillan.Task.Tracker.domain.entities.TaskPriority;
import com.amcvillan.Task.Tracker.domain.entities.TaskStatus;
import com.amcvillan.Task.Tracker.repositories.TaskListRepository;
import com.amcvillan.Task.Tracker.repositories.TaskRepository;
import com.amcvillan.Task.Tracker.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> ListTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null!=task.getId()){
            throw new IllegalArgumentException("Task Already has an Id");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task Must Have a Title");
        }
        TaskPriority taskPriority= Optional.ofNullable(task.getPriority()).
                orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invaild Task ListId Provided"));

        LocalDateTime now = LocalDateTime.now();
        Task tasktoSave = new Task(
        null,
        task.getTitle(),
        task.getDescription(),
                task.getDueDate(),
        taskStatus,
        taskPriority,
        taskList,
        now,
        now
        );
       return taskRepository.save(tasktoSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null==task.getId()){
            throw new IllegalArgumentException("Task Must have an Id");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Task Id Do Not Match");
        }
        if(null == task.getPriority()){
            throw new IllegalArgumentException("Task Priority Must Be Valid");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task Status Must Be Valid");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task Not Found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
