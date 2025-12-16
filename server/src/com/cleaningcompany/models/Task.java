package com.cleaningcompany.models;

public class Task {
    private int id;
    private int clientId;
    private int assignedTo;
    private String status;

    // Конструктор
    public Task(int id, int clientId, int assignedTo, String status) {
        this.id = id;
        this.clientId = clientId;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
