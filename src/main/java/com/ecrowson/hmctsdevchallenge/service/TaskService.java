package com.ecrowson.hmctsdevchallenge.service;

import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task Task);
    Task getTaskById(Long Id);
    List<Task> getAllTasks();
    Task updateTaskStatus(Long id, Status Status);
    void deleteTaskById(Long Id);
}
