package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	static {
		todos.add(new Todo(1, "EH13", "스프링부트 3 강의 완강하기", LocalDate.now().plusMonths(1), false));
		todos.add(new Todo(2, "EH13", "도커, 쿠버네티스 강의 완강하기", LocalDate.now().plusMonths(2), false));
		todos.add(new Todo(3, "EH13", "사이드 프로젝트 완성 하기", LocalDate.now().plusMonths(4), false));
	}

	public List<Todo> findByUsername(String username) {
		return todos;
	}
}
