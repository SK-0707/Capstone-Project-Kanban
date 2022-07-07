//package com.niit.repository;
//
//
//import com.niit.model.Task;
//import com.niit.model.TaskStatus;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class TaskRepositoryTest {
//
//    @Autowired
//    private TaskRepository repository;
//
//    Task task ;
//    TaskStatus taskStatus;
//
//    @BeforeEach
//    public void setUp()
//    {
//        task=new Task(1,"Parading","priventive procedure", LocalDateTime.of(2019, Month.MARCH, 28, 14, 33),LocalDateTime.of(2020, Month.MARCH, 28, 14, 33),"Red",taskStatus);
//
//        taskStatus=new TaskStatus(true,false,false);
//    }
//
//    @AfterEach
//    public void tearDown()
//    {
//        task=null;
//        taskStatus=null;
//    }
//
//    @Test
//    public void saveTaskTest(){
//        repository.insert(task);
//        Task task2=repository.findById(task.getTaskId()).get();
//        assertEquals(task.getTaskId(),task.getTaskId());
//    }
//
//    @Test
//    public void getTaskByIdTest()
//    {
//        repository.save(task);
//        boolean actualResult= repository.existsById(1);
//        assertTrue(actualResult);
//    }
//
//    @Test
//    public void deleteTaskByIdTest()
//    {
//        repository.save(task);
//        repository.delete(task);
//        boolean actualResult= repository.existsById(1);
//        assertFalse(actualResult);
//    }
//}