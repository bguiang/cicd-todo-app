package com.example.cicdtodoapp.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
		taskRepository.save(new Task(task.getName())); // Prevent updates when adding task
	}

	public void deleteTask(long id) {
		taskRepository.deleteById(id);;
	}

	public void updateTask(long id, Task task) throws NoSuchElementException{
		// Prevent mismatched id's from updating
		Optional<Task> taskOptional = taskRepository.findById(id);
		Task update = taskOptional.get();
		update.setName(task.getName());
		taskRepository.save(update);
	}
}
