package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap models) {
		models.put("todos", todoService.findByUsername("EH13"));
		return "listTodos";
	}

	@RequestMapping(value = "add-todo" , method = RequestMethod.GET)
	public String showNewTodoPage() {
		return "todo";
	}

	@RequestMapping(value = "add-todo" , method = RequestMethod.POST)
	public String addNewTodo(@RequestParam String description, ModelMap models) {
		String username = (String) models.get("name");
		todoService.addTodo(username, description, LocalDate.now().plusDays(1), false);
		return "redirect:list-todos";
	}
}
