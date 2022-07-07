package com.niit.service;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Task;
import com.niit.rabbitMq.domain.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {


    public Task saveTask(Task task) throws TaskAlreadyExistException;
    public boolean deleteTask(int id) throws TaskNotFoundException;
    public List<Task> getAllTask();
    Task updateTask(Task oldTask, TaskDTO newTaskDTO);
    public Optional<Task> getTaskById(int id);
    Optional<Task> getTaskByTaskName(String taskName);
}
