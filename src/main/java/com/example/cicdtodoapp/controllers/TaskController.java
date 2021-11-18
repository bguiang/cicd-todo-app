package com.example.cicdtodoapp.controllers;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.services.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addTask(@RequestBody Task task) {
		taskService.addTask(task);
	}
}
