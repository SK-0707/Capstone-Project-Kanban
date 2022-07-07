package com.niit.controller;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Task;
import com.niit.rabbitMq.domain.TaskDTO;
//import com.niit.service.SequenceGeneratorService;
import com.niit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
//this is the backend project for kanban board
@RequestMapping("api/v3/")
public class TaskController {
    @Autowired
    private TaskService taskService;
    private ResponseEntity responseEntity;

//    @Autowired
//    private SequenceGeneratorService service;

    @PostMapping("save")
    public ResponseEntity<?> createTask(@RequestBody Task task) throws TaskAlreadyExistException {
        try{
//            task.setTaskId(service.getSequenceNumber(task.SEQUENCE_NAME));
            taskService.saveTask(task);
            responseEntity = new ResponseEntity(task, HttpStatus.CREATED);
        }
        catch (TaskAlreadyExistException e){
            throw new TaskAlreadyExistException();
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity("Error while adding Task data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") int id) throws TaskNotFoundException {
        try{
            taskService.deleteTask(id);
            responseEntity=new ResponseEntity("Task deleted successfully",HttpStatus.OK);
        }
        catch (TaskNotFoundException e){
            throw new TaskNotFoundException();
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity("Error while Deleting Task data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("tasks")
    public ResponseEntity<?> getAllTask(){
        try {
            responseEntity=new ResponseEntity(taskService.getAllTask(),HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity("Error while fetching Task data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("task")
    public ResponseEntity<?> updateTask( @RequestBody TaskDTO taskDTO){
        try {
            Optional<Task> optTask = taskService.getTaskById(taskDTO.getTaskId());
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        taskService.updateTask(optTask.get(), taskDTO),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No task found with id: " + taskDTO.getTaskId(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("tasks/{taskId}")
    public ResponseEntity<?> getTaskByTaskId(@PathVariable("taskId") int taskId){
        try {
            responseEntity=new ResponseEntity(taskService.getTaskById(taskId),HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity("error while fetching Task By Id",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
