/* package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.model.Task;
import com.niit.model.TaskStatus;
import com.niit.service.TaskService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;
    private Task task1, task2;
    private TaskStatus status1,status2;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp(){
        status1 = new TaskStatus(true,false,false);
        task1 = new Task(1,"go movie","get tickets","4/12/2021","9:00:00AM","red",status1);
        status2  = new TaskStatus(false,true,false);
        task2 = new Task(2,"interval","get snacks","4/12/2021","12:00:00PM","green",status2);
        mockMvc= MockMvcBuilders.standaloneSetup(taskController).build();
    }


    @AfterEach
    public void tearDown(){
        status1=null;
        task1=null;
        status2=null;
        task2=null;
        mockMvc=null;
    }

    @Test
    public void saveTask() throws Exception {
        when(taskService.saveTask(any())).thenReturn(task1);
        mockMvc.perform(post("/api/v3/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsontoString(task1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(taskService,times(1)).saveTask(any());
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
    public void deleteProductDetails() throws Exception {
        mockMvc.perform( delete("/api/v3/delete/1", 1) )
                .andExpect(status().isFound());

    }

    @Test
    public void getAllTasks() throws Exception
    {
        mockMvc.perform(get("/api/v3/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}*/
