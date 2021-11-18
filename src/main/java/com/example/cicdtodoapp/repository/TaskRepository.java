package com.example.cicdtodoapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.cicdtodoapp.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
	public List<Task> findAll();
}
