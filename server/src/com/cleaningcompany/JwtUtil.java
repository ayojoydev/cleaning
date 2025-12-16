import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key"; // Секретный ключ для подписи токенов

    // Генерация JWT
    public static String generateToken(int userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))  // ID пользователя
                .claim("role", role)                 // Роль пользователя
                .setIssuedAt(new Date())             // Дата выпуска токена
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Срок действия токена (1 день)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Подпись токена
                .compact();
    }
}
