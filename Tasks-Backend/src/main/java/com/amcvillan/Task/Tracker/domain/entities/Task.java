package com.amcvillan.Task.Tracker.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;

    @Column(name ="title",nullable = false,updatable = true,length = 100)
    private String Title;

    @Column(name="description",updatable = true,length = 500)
    private String Description;

    @Column(name = "due_date")
    private LocalDateTime DueDate;

    @Column(name = "status" , nullable = false)
    private TaskStatus Status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    @Column(name ="priority" , nullable = false)
    private TaskPriority priority;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name ="updated" ,nullable = false)
    private LocalDateTime updated;

    public Task() {

    }

    public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority priority, TaskList taskList, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        Title = title;
        Description = description;
        DueDate = dueDate;
        Status = status;
        this.priority = priority;
        this.taskList = taskList;
        this.created = created;
        this.updated = updated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        DueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return Status;
    }

    public void setStatus(TaskStatus status) {
        Status = status;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(Title, task.Title) && Objects.equals(Description, task.Description) && Objects.equals(DueDate, task.DueDate) && Status == task.Status && Objects.equals(taskList, task.taskList) && priority == task.priority && Objects.equals(created, task.created) && Objects.equals(updated, task.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Title, Description, DueDate, Status, taskList, priority, created, updated);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", DueDate=" + DueDate +
                ", Status=" + Status +
                ", taskList=" + taskList +
                ", priority=" + priority +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
