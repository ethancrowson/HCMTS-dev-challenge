package com.ecrowson.hmctsdevchallenge;

import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import com.ecrowson.hmctsdevchallenge.repository.TaskRepo;
import com.ecrowson.hmctsdevchallenge.service.TaskServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testCreateTask_DefaultStatusPending() {
        Task task = new Task();
        task.setTitle("New Task");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("New Task");
        savedTask.setStatus(Status.PENDING);

        when(taskRepo.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(task);

        assertEquals(Status.PENDING, result.getStatus());
        assertEquals("New Task", result.getTitle());
        verify(taskRepo, times(1)).save(task);
    }
    @Test
    void testGetTaskById_Found() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");

        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Sample Task", result.getTitle());
    }
    @Test
    void testGetTaskById_NotFound() {
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.getTaskById(1L));
        assertEquals("Cannot find task with id: 1", exception.getMessage());
    }
    @Test
    void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepo.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepo, times(1)).findAll();
    }
    @Test
    void testDeleteTaskById_Found() {
        when(taskRepo.existsById(1L)).thenReturn(true);

        taskService.deleteTaskById(1L);

        verify(taskRepo, times(1)).deleteById(1L);
    }
    @Test
    void testDeleteTaskById_NotFound() {
        when(taskRepo.existsById(1L)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.deleteTaskById(1L));
        assertEquals("Cannot find task with id: 1", exception.getMessage());
    }
    @Test
    void testUpdateTaskStatus_Found() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setStatus(Status.PENDING);

        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task updatedTask = taskService.updateTaskStatus(1L, Status.COMPLETED);
        assertEquals(Status.COMPLETED, updatedTask.getStatus());
        verify(taskRepo, times(1)).save(task);
    }
}
