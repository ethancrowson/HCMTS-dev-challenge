package com.ecrowson.hmctsdevchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecrowson.hmctsdevchallenge.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
