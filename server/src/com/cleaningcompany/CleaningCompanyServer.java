import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.*;

public class CleaningCompanyServer {
    public static void main(String[] args) {
        // Настройка сервера
        Javalin app = Javalin.create().start(8080); // Запуск сервера на порту 8080

        // Роуты API
        app.post("/login", CleaningCompanyServer::login);  // Эндпоинт для логина
        app.get("/tasks", CleaningCompanyServer::getTasks); // Эндпоинт для получения задач
        app.post("/tasks", CleaningCompanyServer::createTask); // Эндпоинт для создания задач
    }

    // Логин и создание JWT
    public static void login(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // Валидация логина (только пример)
        if ("admin".equals(username) && "password".equals(password)) {
            String token = JwtUtil.generateToken(1, "admin");
            ctx.result(token);  // Отправка JWT в ответ
        } else {
            ctx.status(401).result("Invalid credentials");
        }
    }

    // Получение задач
    public static void getTasks(Context ctx) throws SQLException {
        Connection conn = DatabaseConnection.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

        StringBuilder result = new StringBuilder();
        while (rs.next()) {
            result.append("Task ID: ").append(rs.getInt("id")).append("\n");
            result.append("Client ID: ").append(rs.getInt("client_id")).append("\n");
            result.append("Assigned To: ").append(rs.getInt("assigned_to")).append("\n");
            result.append("Status: ").append(rs.getString("status")).append("\n");
        }

        conn.close();
        ctx.result(result.toString());
    }

    // Создание новой задачи
    public static void createTask(Context ctx) throws SQLException {
        int clientId = Integer.parseInt(ctx.formParam("client_id"));
        int assignedTo = Integer.parseInt(ctx.formParam("assigned_to"));
        String status = ctx.formParam("status");

        Connection conn = DatabaseConnection.connect();
        String sql = "INSERT INTO tasks (client_id, assigned_to, status) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, clientId);
        stmt.setInt(2, assignedTo);
        stmt.setString(3, status);
        stmt.executeUpdate();

        conn.close();
        ctx.status(201).result("Task created successfully");
    }
}
