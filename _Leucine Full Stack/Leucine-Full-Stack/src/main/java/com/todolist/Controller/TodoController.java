package com.todolist.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todolist.Service.SummaryService;
import com.todolist.Service.TodoService;
import com.todolist.dto.SummaryRequest;
import com.todolist.model.Todo;

import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    
    @Autowired
    private SummaryService summaryService;


    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAlltodo();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/summarize")
    public Mono<String> summarizeAndSendToSlack(@RequestBody SummaryRequest summaryRequest) {
        return summaryService.summarizeTodos(summaryRequest.getTodos())
                .flatMap(summaryService::sendToSlack);
    }
}