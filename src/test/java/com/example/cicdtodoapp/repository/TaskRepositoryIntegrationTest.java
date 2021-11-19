package com.example.cicdtodoapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.cicdtodoapp.models.Task;

@DataJpaTest
class TaskRepositoryIntegrationTest {

	@Autowired
	private TaskRepository underTest;
	
	@Test
	void save_oneTask_savesTaskWithGeneratedId() {
		// Given
		Task t = new Task("Say hello");
		
		// When
		Task saved = underTest.save(t);
		
		// Then
		assertNotEquals(0, saved.getId());
	}
	
	@Test
	void findAll_threeTasks_returnsListOfTasks() {
		// Given
		Task t1 = underTest.save(new Task("task one"));
		Task t2 = underTest.save(new Task("task two"));
		Task t3 = underTest.save(new Task("task three"));
		
		// When
		List<Task> tasks = underTest.findAll();
		
		// Then
		assertEquals(3, tasks.size());
		
		assertEquals(t1.getId(), tasks.get(0).getId());
		assertEquals("task one", tasks.get(0).getName());

		assertEquals(t2.getId(), tasks.get(1).getId());
		assertEquals("task two", tasks.get(1).getName());
		
		assertEquals(t3.getId(), tasks.get(2).getId());
		assertEquals("task three", tasks.get(2).getName());
	}
	
	@Test 
	void deleteById_oneTaskOutOfThree_deletesTheTaskWithSameId() {
		// Given
		Task t1 = underTest.save(new Task("task one"));
		Task t2 = underTest.save(new Task("task two"));
		Task t3 = underTest.save(new Task("task three"));
		
		// When
		underTest.deleteById(t2.getId());
		
		// Then
		List<Task> tasks = underTest.findAll();
		
		assertEquals(2, tasks.size());
		assertNotEquals(t2.getId(), tasks.get(0).getId());
		assertNotEquals(t2.getId(), tasks.get(1).getId());
	}
}
