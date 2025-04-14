package com.ecrowson.hmctsdevchallenge.service;

import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    @Override
    public Task createTask(Task Task) {
        return null;
    }

    @Override
    public Task getTaskById(Long Id) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public Task updateTaskStatus(Long id, Status Status) {
        return null;
    }

    @Override
    public void deleteTaskById(Long Id) {

    }
}
