/*package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.model.Kanban;
import com.niit.model.Task;
import com.niit.model.TaskStatus;
import com.niit.service.KanbanService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class KanbanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private KanbanService kanbanService;
    private Kanban kanban1,kanban2;
    private Task task1, task2;
    private TaskStatus status1,status2;
    private List<Task> tasks = new ArrayList<Task>();

    @InjectMocks
    private KanbanController kanbanController;

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
        mockMvc= MockMvcBuilders.standaloneSetup(kanbanController).build();
    }


    @AfterEach
    public void tearDown(){
        status1=null;
        task1=null;
        status2=null;
        task2=null;
        kanban1=null;
        kanban2=null;
        mockMvc=null;
    }


    @Test
    public void saveKanban() throws Exception {
        when(kanbanService.saveKanban(any())).thenReturn(kanban1);
        mockMvc.perform(post("/api/v2/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsontoString(task1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(kanbanService,times(1)).saveKanban(any());
    }

    private String jsontoString(final Object obj) {
        String result;
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonContent = objectMapper.writeValueAsString(obj);
            result = jsonContent;
        }
        catch (JsonProcessingException ex){
            result="error while converting to string";
        }
        return result;
    }


    @Test
    public void getAllKanban() throws Exception
    {
        mockMvc.perform(get("/api/v2/kanbans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }}*/
