package com.amcvillan.Task.Tracker.mappers.impl;

import com.amcvillan.Task.Tracker.domain.dto.TaskDto;
import com.amcvillan.Task.Tracker.domain.entities.Task;
import com.amcvillan.Task.Tracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperimpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );

    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
