package com.ecrowson.hmctsdevchallenge.repository;

import com.ecrowson.hmctsdevchallenge.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
