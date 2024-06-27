package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {
	private TodoRepository todoRepository;

	public TodoControllerJPA(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap models) {
		String username = getLoggendInUsername();
		models.addAttribute("todos", todoRepository.findByUsername(username));
		return "listTodos";
	}

	@RequestMapping(value = "add-todo" , method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap models) {
		String username = getLoggendInUsername();
		Todo todo = new Todo(0, username, "", LocalDate.now().plusDays(1), false);
		models.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo" , method = RequestMethod.POST)
	public String addNewTodo(@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}

		String username = getLoggendInUsername();
		todo.setUsername(username);
		todoRepository.save(todo);

		return "redirect:list-todos";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap models) {
		Todo todo = todoRepository.findById(id).get();
		models.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping( value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}

		String username = getLoggendInUsername();
		todo.setUsername(username);
		todoRepository.save(todo);

		return "redirect:list-todos";
	}

	private static String getLoggendInUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
