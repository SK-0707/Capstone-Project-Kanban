//package com.niit.repository;
//
//import com.niit.model.Kanban;
//
//import com.niit.model.Task;
//import com.niit.model.TaskStatus;
//import com.niit.service.KanbanServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class KanbanRepositoryTest {
//
//    Kanban kanban;
//    List<Task> tasks=new ArrayList<>();
//    Task task;
//    TaskStatus taskStatus;
//
//    @Autowired
//    private KanbanRepository repository;
//
//    @BeforeEach
//    public void setUp()
//    {
//        task=new Task(1,"Parading","priventive procedure", LocalDateTime.of(2019, Month.MARCH, 28, 14, 33),LocalDateTime.of(2020, Month.MARCH, 28, 14, 33),"Red",taskStatus);
//
//        taskStatus=new TaskStatus(true,false,false);
//        tasks.add(task);
//        kanban=new Kanban(101,"kanban test",tasks);
//    }
//
//    @AfterEach
//    public void tearDown()
//    {
//        task=null;
//        taskStatus=null;
//        tasks=null;
//        kanban=null;
//    }
//
//    @Test
//    public void saveKanbanTest(){
//        repository.insert(kanban);
//        Kanban kanban2=repository.findById(kanban.getKanbanId()).get();
//        assertEquals(kanban.getKanbanId(),kanban.getKanbanId());
//    }
//
//    @Test
//    public void deleteKanbanByIdTest()
//    {
//        repository.save(kanban);
//        repository.delete(kanban);
//        boolean actualResult= repository.existsById(101);
//        assertFalse(actualResult);
//    }
//
//}