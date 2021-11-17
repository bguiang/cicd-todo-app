package com.example.cicdtodoapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.cicdtodoapp.models.Task;

class TaskControllerTest {

	// Test naming convention: methodname_action_result
	
	@Test
	void getTask_noTasks_retrieveEmptyList() {
		// Given
		TaskController underTest = new TaskController();
		
		// When
		List<Task> tasks = underTest.getTasks();
		
		// Then
		assertNotNull(tasks);
		assertEquals(0, tasks.size());
	}
	
	@Test
	void addTask_singleTask_retrieveOnlyTheSameTask() {
		// Given
		TaskController underTest = new TaskController();
		String taskName = "Say hello world";
		Task task = new Task(taskName);
		
		// When
		underTest.addTask(task);
		
		// Then
		List<Task> tasks = underTest.getTasks();
		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		
		Task t = tasks.get(0);
		assertEquals("Say hello world", t.getName());
	}

	@Test
	void addTask_twoConsecutiveTasks_retrievedInSameOrder() {
		// Given
		TaskController underTest = new TaskController();
		Task task1 = new Task("My first task");
		Task task2 = new Task("My second task");
		
		// When
		underTest.addTask(task1);
		underTest.addTask(task2);
		
		// Then
		List<Task> tasks = underTest.getTasks();
		assertEquals(2, tasks.size());
		assertEquals("My first task", tasks.get(0).getName());
		assertEquals("My second task", tasks.get(1).getName());
	}
}
