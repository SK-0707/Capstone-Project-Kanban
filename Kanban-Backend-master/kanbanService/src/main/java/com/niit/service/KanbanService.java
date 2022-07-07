package com.niit.service;

import com.niit.exception.KanbanNotFoundException;
import com.niit.exception.UserAlreadyExistException;
import com.niit.model.*;
import com.niit.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface KanbanService {

   Kanban saveNewKanban(KanbanDTO kanbanDTO);
   public List<Kanban>  getAllkanban();
   public Kanban updatekanban(Kanban kanban) ;
   public boolean deleteKanban(int kanbanId) throws KanbanNotFoundException ;
   Kanban addNewTaskToKanban(int kanbanId, TaskDTO taskDTO);
   public Optional<Kanban> getKanbanById(int id);
   User registerUser(User user) throws UserAlreadyExistException;
   public  Optional<Kanban> getKanbanByTitle(String title);
   public List<Kanban> getAllKanbanByEmail(String email);
   public Kanban updateTaskToKanban(int kanbanId, Task task);


}
