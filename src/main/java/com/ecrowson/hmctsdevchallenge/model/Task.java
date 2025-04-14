package com.ecrowson.hmctsdevchallenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private long Id;

    @NotBlank(message = "Title may not be blank")
    private String Title;

    private String Description;

    @Enumerated(EnumType.STRING)
    private Status Status;

    private LocalDate DueDate;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }
}
