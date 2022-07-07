/*package com.niit.service;

import com.niit.exception.KanbanAlreadyExistException;
import com.niit.model.Kanban;
import com.niit.model.Task;
import com.niit.model.TaskStatus;
import com.niit.repository.KanbanRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class KanbanServiceTest {

    @Mock
    private KanbanRepository kanbanRepository;

    @InjectMocks
    private KanbanServiceImpl kanbanService;
    private Kanban kanban1,kanban2;
    private Task task1, task2;
    private TaskStatus status1,status2;
    private List<Task> tasks = new ArrayList<Task>();


    @BeforeEach
    public void setUp(){
        status1 = new TaskStatus(true,false,false);
        task1 = new Task(1,"watching movie","nothing","13/02/2021","04:30:44",status1);

        status2  = new TaskStatus(false,true,false);
        task2 = new Task(2,"playing game","outdoor","23/12/2020","05:45:50",status2);
        tasks.add(task1);
        tasks.add(task2);
        kanban1= new Kanban(1,"home",tasks);
        kanban2= new Kanban(2,"Industry",tasks);

    }


    @AfterEach
    public void tearDown(){
        status1=null;
        task1=null;
        status2=null;
        task2=null;
        kanban1=null;
        kanban2=null;

    }

    @Test
    public void saveKanbanTest() throws KanbanAlreadyExistException {
        when(kanbanRepository.save(any())).thenReturn(kanban1);
        assertEquals(kanban1,kanbanService.saveKanban(kanban1));
        verify(kanbanRepository,times(1)).save(any());
        verify(kanbanRepository,times(1)).findById(any());
    }

    @Test
    public void getAllKanbanTest()
    {
        when(kanbanRepository.findAll()).thenReturn(Stream.of(kanban1,kanban2).collect(Collectors.toList()));
        assertEquals(2,kanbanService.getAllkanban().size());
    }}*/
