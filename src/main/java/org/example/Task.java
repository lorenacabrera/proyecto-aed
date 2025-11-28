package org.example;

import java.time.LocalDate;

public class Task {

    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean done;

    // Constructor
    public Task(String title, String description, LocalDate dueDate, boolean done) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
    }

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatus() {
        return done ? "Hecha" : "Pendiente";
    }
}
