package com.cleaningcompany.controllers;

import com.cleaningcompany.models.User;
import com.cleaningcompany.services.UserService;
import io.javalin.http.Context;

public class AuthController {

    private static UserService userService = new UserService();

    // Контроллер для логина
    public static void login(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // Проверка авторизации
        User user = userService.authenticate(username, password);
        if (user != null) {
            // Генерация JWT
            String token = JwtUtil.generateToken(user.getId(), user.getRole());
            ctx.result(token);
        } else {
            ctx.status(401).result("Invalid credentials");
        }
    }
}
