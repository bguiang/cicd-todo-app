package com.example.cicdtodoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.repository.TaskRepository;
import com.example.cicdtodoapp.service.TaskService;

//Test naming convention: methodname_action_result
class TaskServiceTest {

	private TaskService underTest;
	private TaskRepository taskRepository;
	
	@BeforeEach
	private void setup() {
		taskRepository = Mockito.mock(TaskRepository.class);
		underTest = new TaskService(taskRepository);
	}
	
	@Test
	void getTask_anyTaskList_returnsTasksFromRepository() {
		// Given
		List<Task> anyTaskList = new ArrayList<>();
		
		// When
		when(taskRepository.findAll()).thenReturn(anyTaskList);
		List<Task> tasks = underTest.getTasks();
		
		// Then
		assertEquals(anyTaskList, tasks);
	}
	
	@Test
	void addTask_oneTask_savesTheTask() {
		// Given
		String taskName = "Say hello world";
		Task task = new Task(taskName);
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		
		// When
		underTest.addTask(task);
		
		// Then
		verify(taskRepository).save(task);
	}

	@Test
	void deleteTask_anyId_deletesTaskWithTheSameId() {
		// Given
		// When
		underTest.deleteTask(5l);
		
		// Then
		verify(taskRepository).deleteById(5l);
	}
}
