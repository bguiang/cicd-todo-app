package com.example.cicdtodoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.service.TaskService;

class TaskServiceTest {

	// Test naming convention: methodname_action_result
	
	@Test
	void getTask_noTasks_returnsEmptyList() {
		// Given
		TaskService underTest = new TaskService();
		
		// When
		List<Task> tasks = underTest.getTasks();
		
		// Then
		assertNotNull(tasks);
		assertEquals(0, tasks.size());
	}
	
	@Test
	void addTask_singleTask_savesTheTask() {
		// Given
		TaskService underTest = new TaskService();
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
		assertEquals(1L, t.getId());
	}

	@Test
	void addTask_twoConsecutiveTasks_savesTasksInSameOrder() {
		// Given
		TaskService underTest = new TaskService();
		Task task1 = new Task("My first task");
		Task task2 = new Task("My second task");
		
		// When
		underTest.addTask(task1);
		underTest.addTask(task2);
		
		// Then
		List<Task> tasks = underTest.getTasks();
		assertEquals(2, tasks.size());
		assertEquals("My first task", tasks.get(0).getName());
		assertEquals(1L, tasks.get(0).getId());
		assertEquals("My second task", tasks.get(1).getName());
		assertEquals(2L, tasks.get(1).getId());
	}

}
