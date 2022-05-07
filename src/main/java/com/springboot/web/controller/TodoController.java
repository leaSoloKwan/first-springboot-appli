package com.springboot.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
		return "todo";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.POST)
	public String AddTodo(ModelMap model,@RequestParam String desc) {
		service.addTodo((String)model.get("name"), desc, new Date(), false);
		model.put("todos", service.retrieveTodos((String) model.get("name")));
		return "redirect:/list-todos";//sinon url rest /add-todo avec la page llst_todos
	}

	

}
