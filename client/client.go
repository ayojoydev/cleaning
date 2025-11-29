package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
)

func main() {
	// URL нашего Java-сервера
	url := "http://localhost:8080/hello"

	resp, err := http.Get(url)
	if err != nil {
		fmt.Println("Ошибка при запросе:", err)
		os.Exit(1)
	}
	defer resp.Body.Close()

	fmt.Println("Статус ответа:", resp.Status)

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Ошибка при чтении тела ответа:", err)
		os.Exit(1)
	}

	fmt.Println("Тело ответа:", string(body))
}
