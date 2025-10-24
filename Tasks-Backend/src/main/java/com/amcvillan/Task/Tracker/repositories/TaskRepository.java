package com.amcvillan.Task.Tracker.repositories;

import com.amcvillan.Task.Tracker.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task>findByTaskListIdAndId(UUID taskListId, UUID id);
    void deleteByTaskListIdAndId(UUID taskListId, UUID id);
}
