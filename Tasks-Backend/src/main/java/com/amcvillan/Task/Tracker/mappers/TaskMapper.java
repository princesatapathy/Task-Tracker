package com.amcvillan.Task.Tracker.mappers;

import com.amcvillan.Task.Tracker.domain.dto.TaskDto;
import com.amcvillan.Task.Tracker.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
