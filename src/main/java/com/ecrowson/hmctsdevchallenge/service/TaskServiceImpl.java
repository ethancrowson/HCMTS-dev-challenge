package com.ecrowson.hmctsdevchallenge.service;

import com.ecrowson.hmctsdevchallenge.model.Status;
import com.ecrowson.hmctsdevchallenge.model.Task;
import com.ecrowson.hmctsdevchallenge.repository.TaskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo repo;

    public TaskServiceImpl(TaskRepo repo) {
        this.repo = repo;
    }

    @Override
    public Task createTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(com.ecrowson.hmctsdevchallenge.model.Status.PENDING);
        }
        return repo.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find task with id: " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    @Override
    public Task updateTaskStatus(Long id, Status status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return repo.save(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Cannot find task with id: " + id);
        }
        repo.deleteById(id);
    }
}
