package com.niit.service;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Task;
import com.niit.rabbitMq.domain.TaskDTO;
import com.niit.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task saveTask(Task task) throws TaskAlreadyExistException {
        if(taskRepository.findById(task.getTaskId()).isPresent()){
            throw new TaskAlreadyExistException();
        }
        return taskRepository.save(task);
    }

    @Override
    public boolean deleteTask(int id) throws TaskNotFoundException {
        boolean result=false;
        if(taskRepository.findById(id).isEmpty()){
            throw new TaskNotFoundException();
        }
        else{
            taskRepository.deleteById(id);
            result=true;
        }
        return result;
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task oldTask, TaskDTO newTaskDTO) {
        return taskRepository.save(updateTaskFromDTO(oldTask, newTaskDTO));
    }


    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public Optional<Task> getTaskByTaskName(String taskName) {
        return taskRepository.findByTaskName(taskName);
    }

    private Task updateTaskFromDTO(Task task, TaskDTO taskDTO){
        if(Optional.ofNullable(taskDTO.getTaskName()).isPresent()){
            task.setTaskStatus(taskDTO.getTaskStatus());
        }

        if (Optional.ofNullable((taskDTO.getDescription())).isPresent()) {
            task.setDescription(taskDTO.getDescription());
        }

        if (Optional.ofNullable((taskDTO.getColor())).isPresent()) {
            task.setColor(taskDTO.getColor());
        }

        if (Optional.ofNullable((taskDTO.getTaskStatus())).isPresent()) {
            task.setTaskStatus(taskDTO.getTaskStatus());
        }
        return task;
    }
}
