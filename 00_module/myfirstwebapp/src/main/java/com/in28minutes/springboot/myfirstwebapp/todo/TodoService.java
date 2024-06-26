package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount = 0;

	static {
		todos.add(new Todo(++todoCount, "EH13", "스프링부트 3 강의 완강하기", LocalDate.now().plusMonths(1), false));
		todos.add(new Todo(++todoCount, "EH13", "도커, 쿠버네티스 강의 완강하기", LocalDate.now().plusMonths(2), false));
		todos.add(new Todo(++todoCount, "EH13", "사이드 프로젝트 완성 하기", LocalDate.now().plusMonths(4), false));
	}

	public List<Todo> findByUsername(String username) {
		return todos;
	}

	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		todos.add(new Todo(++todoCount, username, description, targetDate, done));
	}
}
