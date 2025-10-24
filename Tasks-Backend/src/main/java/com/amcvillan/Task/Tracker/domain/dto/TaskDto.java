package com.amcvillan.Task.Tracker.domain.dto;

import com.amcvillan.Task.Tracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        com.amcvillan.Task.Tracker.domain.entities.TaskPriority priority, TaskStatus status
){


}
