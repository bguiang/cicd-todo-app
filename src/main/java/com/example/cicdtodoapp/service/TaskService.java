package com.example.cicdtodoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.repository.TaskRepository;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	public void addTask(Task task) {
		taskRepository.save(task);
	}

	public void deleteTask(long id) {
		taskRepository.deleteById(id);;
	}
}
