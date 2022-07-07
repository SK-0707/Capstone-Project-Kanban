/*package com.niit.service;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Task;
import com.niit.model.TaskStatus;
import com.niit.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;


    @InjectMocks
    private TaskServiceImpl taskService;
    private Task task1, task2;
    private TaskStatus status1,status2;

    @BeforeEach
    public void setUp(){
        status1 = new TaskStatus(true,false,false);
        task1 = new Task(1,"go movie","get tickets","4/12/2021","9:00:00AM","red",status1);
        status2  = new TaskStatus(false,true,false);
        task2 = new Task(2,"interval","get snacks","4/12/2021","12:00:00PM","green",status2);

    }

    @AfterEach
    public void tearDown(){
        status1=null;
        task1=null;
        status2=null;
        task2=null;

    }


    @Test
    public void saveTaskTest() throws TaskAlreadyExistException {
        when(taskRepository.save(any())).thenReturn(task1);
        assertEquals(task1,taskService.saveTask(task1));
        verify(taskRepository,times(1)).save(any());
        verify(taskRepository,times(1)).findById(any());
    }


    @Test
    public void getAllTaskTest()
    {
        when(taskRepository.findAll()).thenReturn(Stream.of(task1,task2).collect(Collectors.toList()));
        assertEquals(2,taskService.getAllTask().size());
    }

    @Test
    public void deleteTaskTest()throws TaskNotFoundException
    {
        taskRepository.save(task1);
        taskRepository.deleteById(task1.getTaskId());
        Optional optional = taskRepository.findById(task1.getTaskId());
        assertEquals(Optional.empty(), optional);
    }
}
*/