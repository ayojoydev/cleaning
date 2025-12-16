package com.cleaningcompany.services;

import com.cleaningcompany.models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("assigned_to"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void createTask(Task task) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO tasks (client_id, assigned_to, status) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, task.getClientId());
            stmt.setInt(2, task.getAssignedTo());
            stmt.setString(3, task.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
