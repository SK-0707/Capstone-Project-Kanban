package com.niit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Document
public class Kanban {
    @Id

    private int kanbanId;
    private String kanbanTitle;
    private List<Task> tasks;
    private String email;

    public Kanban() {
    }

    public Kanban(int kanbanId, String kanbanTitle, List<Task> tasks, String email) {
        this.kanbanId = kanbanId;
        this.kanbanTitle = kanbanTitle;
        this.tasks = tasks;
        this.email = email;
    }

    public int getKanbanId() {
        return kanbanId;
    }

    public void setKanbanId(int kanbanId) {
        this.kanbanId = kanbanId;
    }

    public String getKanbanTitle() {
        return kanbanTitle;
    }

    public void setKanbanTitle(String kanbanTitle) {
        this.kanbanTitle = kanbanTitle;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addTask(Task task) {

        if (Objects.isNull(tasks)) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }
}