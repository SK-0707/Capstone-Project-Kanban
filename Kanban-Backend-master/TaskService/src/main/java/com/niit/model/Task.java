package com.niit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Task {

    @Transient
    public static final String SEQUENCE_NAME="user_sequence";

    @Id
    private int taskId;
    private String taskName;
    private String description;
    private String color;
    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(int taskId, String taskName, String description, String color, TaskStatus taskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.color = color;
        this.taskStatus = taskStatus;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
