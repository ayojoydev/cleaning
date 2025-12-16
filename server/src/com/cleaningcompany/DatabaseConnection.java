import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Подключение к базе данных PostgreSQL
    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cleaning_company"; // URL базы данных
        String user = "postgres";  // Имя пользователя
        String password = "your_password";  // Пароль
        return DriverManager.getConnection(url, user, password);  // Подключение к БД
    }
}
