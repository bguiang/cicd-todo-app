package com.example.cicdtodoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
	void addTask_oneTask_savesNewTask() {
		// Given
		String taskName = "Say hello world";
		long idToBeIgnoredWhenSaved = 5L;
		Task task = new Task(taskName, idToBeIgnoredWhenSaved);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(task);
		
		// When
		underTest.addTask(task);
		
		// Then
		ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		verify(taskRepository).save(taskCaptor.capture());
		
		Task capturedSaveTaskParam = taskCaptor.getValue();
		assertEquals("Say hello world", capturedSaveTaskParam.getName());
		assertEquals(0, capturedSaveTaskParam.getId());
	}

	@Test
	void deleteTask_anyId_deletesTaskWithTheSameId() {
		// Given
		// When
		underTest.deleteTask(5l);
		
		// Then
		verify(taskRepository).deleteById(5l);
	}
	
	@Test
	void updateTask_anyId_updatesTaskWithTheSameId() {
		// Given
		Task found = new Task("don't say hello", 5L);
		Task update = new Task("say hello");
		
		// When 
		when(taskRepository.findById(5L)).thenReturn(Optional.of(found));
		underTest.updateTask(5L, update);
		
		// Then
		ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		verify(taskRepository).save(taskCaptor.capture());
		
		Task saveTaskInput = taskCaptor.getValue();
		assertEquals(5L, saveTaskInput.getId()); // Saved has to match the id
		assertEquals("say hello", saveTaskInput.getName()); // updated
	}
	
	@Test
	void updateTask_idDoesNotExist_doesNotSave() {
		// Given
		Task update = new Task("say hello");
		
		// When 
		when(taskRepository.findById(5L)).thenReturn(Optional.ofNullable(null));
		
		// Then
		assertThrows(
			NoSuchElementException.class, 
			() -> {
				underTest.updateTask(5L, update);
			}
		);
		verify(taskRepository, times(0)).save(Mockito.any());
	}
	
}
