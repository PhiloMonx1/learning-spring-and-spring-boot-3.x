package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	private TodoRepository todoRepository;

	public TodoResource(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoRepository.findByUsername(username);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
		return todoRepository.findById(id).get();
	}

	@PostMapping("/users/{username}/todos")
	public Todo addTodo(@PathVariable String username, @RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
		todo.setId(id);
		todo.setUsername(username);
		todo.setDone(false);
		todoRepository.save(todo);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
