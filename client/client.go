package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
	"strings"
)

func main() {
	// Запрашиваем токен от сервера (логин)
	loginData := "username=admin&password=password" // Данные для логина
	resp, err := http.Post("http://localhost:8080/login", "application/x-www-form-urlencoded",
		strings.NewReader(loginData))
	if err != nil {
		fmt.Println("Ошибка при запросе:", err)
		os.Exit(1)
	}
	defer resp.Body.Close()

	// Чтение токена
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Ошибка при чтении тела ответа:", err)
		os.Exit(1)
	}

	token := string(body)
	fmt.Println("JWT токен:", token)

	// Пример использования токена для запроса задач
	client := &http.Client{}
	req, err := http.NewRequest("GET", "http://localhost:8080/tasks", nil)
	req.Header.Add("Authorization", "Bearer "+token)

	if err != nil {
		fmt.Println("Ошибка создания запроса:", err)
		return
	}

	resp, err = client.Do(req)
	if err != nil {
		fmt.Println("Ошибка при отправке запроса:", err)
		return
	}
	defer resp.Body.Close()

	body, err = io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Ошибка при чтении ответа:", err)
		return
	}

	fmt.Println("Задачи с сервера:", string(body))
}
