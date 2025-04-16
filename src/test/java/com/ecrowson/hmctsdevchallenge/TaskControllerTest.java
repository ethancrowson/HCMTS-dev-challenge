package com.ecrowson.hmctsdevchallenge;

import com.ecrowson.hmctsdevchallenge.controller.TaskController;
import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import com.ecrowson.hmctsdevchallenge.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setStatus(Status.PENDING);
    }

    @Test
    void testCreateTask() {
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        Task result = taskController.createTask(task);

        assertEquals(task.getTitle(), result.getTitle());
    }
    @Test
    void testGetTaskById_Found() {
        when(taskService.getTaskById(1L)).thenReturn(task);

        Task result = taskController.getTaskById(1L).getBody();

        assertEquals(task.getTitle(), result.getTitle());
    }
}
