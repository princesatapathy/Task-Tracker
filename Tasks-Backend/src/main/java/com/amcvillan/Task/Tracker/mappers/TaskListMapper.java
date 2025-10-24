package com.amcvillan.Task.Tracker.mappers;

import com.amcvillan.Task.Tracker.domain.dto.TaskListDto;
import com.amcvillan.Task.Tracker.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);

}
