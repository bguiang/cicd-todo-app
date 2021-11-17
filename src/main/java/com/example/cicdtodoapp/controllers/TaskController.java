package com.example.cicdtodoapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.services.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

	private TaskService taskService = new TaskService(); // not @Service yet
	
	@GetMapping
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	public void addTask(Task task) {
		taskService.addTask(task);
	}
}
