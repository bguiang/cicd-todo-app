package com.example.cicdtodoapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class TaskControllerIntegrationTest {

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
		// When
		// Then
		mvc.perform(get("/api/v1/tasks"))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isEmpty());
	}

}
