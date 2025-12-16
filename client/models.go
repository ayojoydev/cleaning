package main

type Task struct {
	ID         int    `json:"id"`
	ClientID   int    `json:"client_id"`
	AssignedTo int    `json:"assigned_to"`
	Status     string `json:"status"`
}
