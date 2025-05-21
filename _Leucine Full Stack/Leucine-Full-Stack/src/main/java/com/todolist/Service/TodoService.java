package com.todolist.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.Repository.TodoRepository;
import com.todolist.model.Todo;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getAlltodo(){
		return todoRepository.findAll();
	}
	
	public Todo addTodo (Todo todo){
		return todoRepository.save(todo);
	}
	
	public void deleteTodo(Long id){
	 todoRepository.deleteById(id);;	
	}

}
