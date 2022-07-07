package com.niit.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Task {

    @Id
    private int taskId;
    private String taskName;
    private String description;
    private TaskStatus taskStatus;
    private String assignTo;
    private String date;

    public Task() {
    }

    public Task(int taskId, String taskName, String description, TaskStatus taskStatus, String assignTo, String date) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.taskStatus = taskStatus;
        this.assignTo = assignTo;
        this.date = date;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}