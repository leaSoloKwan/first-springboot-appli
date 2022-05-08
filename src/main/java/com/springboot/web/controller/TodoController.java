package com.springboot.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.web.model.Todo;
import com.springboot.web.service.LoginService;
import com.springboot.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	@Autowired
	TodoService service;
	
	@RequestMapping(value="/list-todos",method=RequestMethod.GET)
	public String showTodoList(ModelMap model) {
		String name_s = (String) model.get("name");
		model.put("todos", service.retrieveTodos(name_s));
		return "list-todos";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo",new Todo(0, (String) model.get("name"), "", new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.POST)
	// @Valid to enable the validation on model
	// BindingResult contain the result "if the validation succeeded or not"
	public String AddTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()){
			return "todo"; // remain same page
		}
		service.addTodo((String)model.get("name"), todo.getDesc(), new Date(), false);
		model.put("todos", service.retrieveTodos((String) model.get("name")));
		return "redirect:/list-todos";//sinon url rest /add-todo avec la page llst_todos
	}
	@RequestMapping(value="/delete-todo",method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo",method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		service.deleteTodo(id);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "todo"; // remain same page
		}
		//recup l'id dans le form jsp mais il manque encore le user qui dans session
		todo.setUser((String) model.get("name")); 
		service.updateTodo(todo);
		return "redirect:/list-todos";
	}

	

}
