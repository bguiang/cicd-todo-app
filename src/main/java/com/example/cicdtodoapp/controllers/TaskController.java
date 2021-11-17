package com.example.cicdtodoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.cicdtodoapp.models.Task;

public class TaskController {

	private List<Task> tasks = new ArrayList<>();
	
	public void addTask(Task task) {
		tasks.add(task);
		
	}

	public List<Task> getTasks() {
		return tasks;
	}

}
