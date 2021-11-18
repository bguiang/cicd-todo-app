package com.example.cicdtodoapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cicdtodoapp.models.Task;
import com.example.cicdtodoapp.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class TaskControllerIntegrationTest {

	@MockBean
	private TaskService taskService;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void getTask_noUpdates_returnsStatusOk() throws Exception {
		// Given
		// When
		// Then
		mvc.perform(get("/api/v1/tasks"))
		.andExpect(status().isOk());
	}
	
	@Test
	void getTask_noUpdates_returnsEmptyList() throws Exception {
		// Given
		List<Task> tasks = new ArrayList<>();
		
		// When
		when(taskService.getTasks()).thenReturn(tasks);
		
		// Then
		mvc.perform(get("/api/v1/tasks"))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isEmpty());
	}
	
	@Test
	void addTask_validTask_returnsStatusCreated() throws Exception {
		// Given
		Task task = new Task("say hello world");
		// When
		// Then
		mvc.perform(post("/api/v1/tasks")
				.content(asJsonString(task))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	void addTask_validTask_addsTask() throws Exception {
		// Given
		Task task = new Task("say hello world");
		// When
		
		// Then
		mvc.perform(post("/api/v1/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(task))
		);
		
		ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		Mockito.verify(taskService).addTask(taskCaptor.capture());
		
		assertEquals("say hello world", taskCaptor.getValue().getName());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
