package com.cleaningcompany.controllers;

import com.cleaningcompany.models.Task;
import com.cleaningcompany.services.TaskService;
import io.javalin.http.Context;

import java.util.List;

public class TaskController {

    private static TaskService taskService = new TaskService();

    // Получение всех задач
    public static void getTasks(Context ctx) {
        List<Task> tasks = taskService.getAllTasks();
        ctx.json(tasks); // Отправляем задачи в формате JSON
    }

    // Создание новой задачи
    public static void createTask(Context ctx) {
        int clientId = Integer.parseInt(ctx.formParam("client_id"));
        int assignedTo = Integer.parseInt(ctx.formParam("assigned_to"));
        String status = ctx.formParam("status");

        Task task = new Task(0, clientId, assignedTo, status); // ID 0 — новый объект, не сохранённый ещё
        taskService.createTask(task);
        ctx.status(201).result("Task created successfully");
    }
}
