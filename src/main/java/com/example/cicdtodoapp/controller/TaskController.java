package com.example.cicdtodoapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.service.TaskService;

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
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteTask(@PathVariable long id) {
		taskService.deleteTask(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateTask(@PathVariable long id, @RequestBody Task task) {
		taskService.updateTask(id, task);
	}
}
