package com.cleaningcompany.services;

import com.cleaningcompany.models.User;

import java.sql.*;

public class UserService {

    public User authenticate(String username, String password) {
        User user = null;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
