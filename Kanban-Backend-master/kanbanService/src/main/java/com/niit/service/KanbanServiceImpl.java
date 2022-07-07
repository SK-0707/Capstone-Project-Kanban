package com.niit.service;

import com.niit.exception.KanbanNotFoundException;
import com.niit.exception.UserAlreadyExistException;
import com.niit.model.*;
import com.niit.proxy.UserProxy;
import com.niit.model.TaskDTO;
import com.niit.repository.KanbanRepository;
import com.niit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KanbanServiceImpl implements KanbanService {

    @Autowired
    private KanbanRepository kanbanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProxy userProxy;




    @Override
    public Kanban saveNewKanban(KanbanDTO kanbanDTO)  {

        return kanbanRepository.save(convertDTOToKanban(kanbanDTO));
    }

    @Override
    public List<Kanban> getAllkanban() {

        return kanbanRepository.findAll();
    }

    @Override
    public Kanban updatekanban(Kanban kanban) {

        return kanbanRepository.save(kanban);
    }

    @Override
    public boolean deleteKanban(int kanbanId) throws KanbanNotFoundException {
        boolean result = false;
        if (kanbanRepository.findById(kanbanId).isEmpty()) {
            throw new KanbanNotFoundException();
        } else {
            kanbanRepository.deleteById(kanbanId);
            result = true;
        }
        return result;
    }

    @Override
    public Kanban addNewTaskToKanban(int kanbanId, TaskDTO taskDTO) {
        Kanban kanban = kanbanRepository.findById(kanbanId).get();
        kanban.addTask(convertDTOToTask(taskDTO));

        return kanbanRepository.save(kanban);
    }


    @Override
    public Optional<Kanban> getKanbanById(int id) {
        return kanbanRepository.findById(id);
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        if(userRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistException();
        }
        ResponseEntity response = userProxy.SaveUser(user);
        System.out.println(response.getBody());
        return userRepository.save(user);

    }

    @Override
    public Optional<Kanban> getKanbanByTitle(String title) {
        return kanbanRepository.findByKanbanTitle(title);
    }

    @Override
    public List<Kanban> getAllKanbanByEmail(String email) {
        List<Kanban> kanbanList = new ArrayList<>();
        kanbanRepository.findByEmail(email).forEach(kanbanList::add);
        return kanbanList;
    }


    @Override
    public Kanban updateTaskToKanban(int kanbanId,Task task) {
        Kanban kanban = kanbanRepository.findById(kanbanId).get();
        List<Task> taskList=kanban.getTasks();
        Task myTask = null;
        for (Task  task1 : taskList) {
           if(task1.getTaskId()==task.getTaskId()) {
               myTask = task1;
               break;
           }
        }
        taskList.remove(myTask);
        taskList.add(task);
        kanban.setTasks(taskList);
        return kanbanRepository.save(kanban);
    }


    private Kanban convertDTOToKanban(KanbanDTO kanbanDTO){
        Kanban kanban = new Kanban();
        kanban.setKanbanTitle(kanbanDTO.getKanbanTitle());
        kanban.setKanbanId(kanbanDTO.getKanbanId());
        kanban.setEmail(kanbanDTO.getEmail());
        return kanban;
    }

    private Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskId(taskDTO.getTaskId());
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());
        task.setAssignTo(taskDTO.getAssignTo());
        task.setDate(taskDTO.getDate());
        return task;
    }
}