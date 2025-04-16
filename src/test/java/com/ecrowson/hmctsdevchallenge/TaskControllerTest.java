package com.ecrowson.hmctsdevchallenge;

import com.ecrowson.hmctsdevchallenge.controller.TaskController;
import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import com.ecrowson.hmctsdevchallenge.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @Test
    void testGetTaskById_NotFound() {
        when(taskService.getTaskById(1L)).thenThrow(new EntityNotFoundException("Task not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            taskController.getTaskById(1L);
        });

        assertEquals("Task not found", exception.getMessage());
    }
    @Test
    void testUpdateTaskStatus_Found() {
        task.setStatus(Status.COMPLETED);

        when(taskService.updateTaskStatus(1L, Status.COMPLETED)).thenReturn(task);

        ResponseEntity<Task> result = taskController.updateTaskStatus(1L, Status.COMPLETED);

        assertEquals(Status.COMPLETED, result.getBody().getStatus());
        verify(taskService, times(1)).updateTaskStatus(1L, Status.COMPLETED);
    }
    @Test
    void testDeleteTask() {
        doNothing().when(taskService).deleteTaskById(1L);

        taskController.deleteTask(1L);

        verify(taskService, times(1)).deleteTaskById(1L);
    }
    @Test
    void testGetAllTasks() {
        when(taskService.getAllTasks()).thenReturn(List.of(task));

        List<Task> result = taskController.getAllTasks();

        assertEquals(1, result.size());
        assertEquals(task.getTitle(), result.get(0).getTitle());
    }
}
