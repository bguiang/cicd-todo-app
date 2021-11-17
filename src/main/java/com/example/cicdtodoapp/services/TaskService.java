package com.example.cicdtodoapp.services;

import java.util.ArrayList;
import java.util.List;

import com.example.cicdtodoapp.models.Task;

public class TaskService {
	private List<Task> tasks = new ArrayList<>();
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
}
