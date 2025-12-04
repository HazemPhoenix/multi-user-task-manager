package com.hazem.model;

public class Task {
    private int id;
    private String name;
    private int userId;
    private boolean isDone;

    public Task() {
    }

    public Task(String name, int userId, boolean isDone) {
        this.name = name;
        this.userId = userId;
        this.isDone = isDone;
    }

    public Task(int id, String name, boolean isDone) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public Task(int id, String name, int userId, boolean isDone) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
