package com.ecrowson.hmctsdevchallenge;

import com.ecrowson.hmctsdevchallenge.repository.TaskRepo;
import com.ecrowson.hmctsdevchallenge.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaskServiceImplTest {

    private TaskServiceImpl taskService;
    private TaskRepo taskRepo;

    @BeforeEach
    public void setUp() {
        taskRepo = Mockito.mock(TaskRepo.class);
        taskService = new TaskServiceImpl(taskRepo);
    }

    @Test
    public void test() {

    }
}
