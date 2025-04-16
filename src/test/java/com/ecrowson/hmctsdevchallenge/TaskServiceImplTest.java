package com.ecrowson.hmctsdevchallenge;

import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import com.ecrowson.hmctsdevchallenge.repository.TaskRepo;
import com.ecrowson.hmctsdevchallenge.service.TaskServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    private TaskServiceImpl taskService;
    private TaskRepo taskRepo;

    @BeforeEach
    public void setUp() {
        taskRepo = Mockito.mock(TaskRepo.class);
        taskService = new TaskServiceImpl(taskRepo);
    }

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
}
