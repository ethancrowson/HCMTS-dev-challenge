package com.ecrowson.hmctsdevchallenge.service;

import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import com.ecrowson.hmctsdevchallenge.repository.TaskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo Repo;

    public TaskServiceImpl(TaskRepo Repo) {
        this.Repo = Repo;
    }

    @Override
    public Task createTask(Task Task) {
        if (Task.getStatus() == null) {
            Task.setStatus(com.ecrowson.hmctsdevchallenge.model.Status.PENDING);
        }
        return Repo.save(Task);
    }

    @Override
    public Task getTaskById(Long Id) {
        return Repo.findById(Id).orElseThrow(() -> new EntityNotFoundException("Cannot find taks with id: " + Id));
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
