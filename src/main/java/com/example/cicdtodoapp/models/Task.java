package com.example.cicdtodoapp.models;

//@Entity
public class Task {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	public Task() {}
	
	public Task(String name) {
		this.name = name;
	}
	
	public Task(String name, long id) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
