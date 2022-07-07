package com.niit.controller;

import com.niit.exception.KanbanNotFoundException;
import com.niit.exception.UserAlreadyExistException;
import com.niit.model.*;
import com.niit.model.TaskDTO;
import com.niit.service.KanbanService;
//import com.niit.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin("*")
@RequestMapping("api/v2/")
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;
    private ResponseEntity responseEntity;

//    @Autowired
//    private SequenceGeneratorService service;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistException {
        try {
            responseEntity =  new ResponseEntity<>(kanbanService.registerUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistException e)
        {
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }

@PostMapping("save")
    public ResponseEntity<?> createKanban(@RequestBody KanbanDTO kanbanDTO)  {
        try {
//            kanbanDTO.setKanbanId(service.getSequenceNumber(kanbanDTO.SEQUENCE_NAME));
            System.out.println(kanbanDTO.getKanbanTitle());
            kanbanService.saveNewKanban(kanbanDTO);
            responseEntity = new ResponseEntity(kanbanDTO, HttpStatus.CREATED);

        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Error while adding kanban board", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("kanbans")
    public ResponseEntity<?> getAllKanban() {
        try {
            responseEntity = new ResponseEntity(kanbanService.getAllkanban(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Error while fetching kanban board data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @DeleteMapping("delete/{kanbanId}")
    public ResponseEntity<?> deleteKanban(@PathVariable("kanbanId") int kanbanId) throws KanbanNotFoundException {
        try {
            kanbanService.deleteKanban(kanbanId);
            responseEntity = new ResponseEntity("Task deleted successfully", HttpStatus.OK);
        } catch (KanbanNotFoundException e) {
            throw new KanbanNotFoundException();
        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Error while Deleting kanban board data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
    @PutMapping("update")
    public ResponseEntity<?> updateKanban(@RequestBody Kanban kanban){
        try {

                return new ResponseEntity<Kanban>(
                        kanbanService.updatekanban(kanban),

                        HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Error while updating kanban board data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("{kanbanId}/tasks")
    public ResponseEntity<?> createTaskAssignedToKanban(@PathVariable int kanbanId, @RequestBody TaskDTO taskDTO){
        try {
//            taskDTO.setTaskId(service.getSequenceNumber(taskDTO.SEQUENCE_NAME));
            return new ResponseEntity<Task>(
                    (MultiValueMap<String, String>) kanbanService.addNewTaskToKanban(kanbanId,taskDTO),HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Error while creating kanban board data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("{kanbanId}/update")
    public ResponseEntity<?> UpdateTaskAssignToKanban(@PathVariable int kanbanId, @RequestBody Task task){
        try {

            return new ResponseEntity<Kanban>(kanbanService.updateTaskToKanban(kanbanId,task),HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity("Error while Updating kanban board data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }




    @GetMapping("get-kanban/{kanbanTitle}")
    public ResponseEntity<?> getKanbanByTitle(@PathVariable("kanbanTitle") String kanbanTitle) {
        try {

            responseEntity = new ResponseEntity(kanbanService.getKanbanByTitle(kanbanTitle), HttpStatus.OK);

        } catch (Exception ex) {
            responseEntity = new ResponseEntity("error while fetching Kanban data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }



    @GetMapping("kanban/{kanbanId}")
    public ResponseEntity<?> getKanbanByKanbanId(@PathVariable("kanbanId") int kanbanId){
        try {
            responseEntity=new ResponseEntity(kanbanService.getKanbanById(kanbanId),HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity("error while fetching Kanban By Id",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("{kanbanId}/tasks")
    public ResponseEntity<?> getAllTasksInKanban(@PathVariable int kanbanId){
        try {
            Optional<Kanban> optKanban = kanbanService.getKanbanById(kanbanId);
            if (optKanban.isPresent()) {
                return new ResponseEntity<>(
                        optKanban.get().getTasks(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No kanban found with id: " + kanbanId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("kanbans/{email}")
    public ResponseEntity<?> getAllKanbanByEmail(@PathVariable("email") String email) {
        try {
            responseEntity = new ResponseEntity(kanbanService.getAllKanbanByEmail(email), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Error while fetching kanban board data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
