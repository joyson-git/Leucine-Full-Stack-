package com.todolist.dto;

import java.util.List;

public class SummaryRequest {

	private List<String> todos;

	public List<String> getTodos() {
		return todos;
	}

	public void setTodos(List<String> todos) {
		this.todos = todos;
	}
	
}
