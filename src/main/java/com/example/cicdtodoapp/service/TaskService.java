package com.example.cicdtodoapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cicdtodoapp.models.Task;

@Service
public class TaskService {
	private List<Task> tasks = new ArrayList<>();
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		task.setId(tasks.size() + 1); // generate ID
		tasks.add(task);
	}
}
