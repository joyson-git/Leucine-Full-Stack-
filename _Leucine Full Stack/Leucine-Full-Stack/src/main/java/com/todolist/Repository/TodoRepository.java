package com.todolist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
