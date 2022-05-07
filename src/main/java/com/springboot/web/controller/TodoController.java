package com.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.web.service.LoginService;
import com.springboot.web.service.TodoService;

@Controller
public class TodoController {
	@Autowired
	TodoService service;
	
	@RequestMapping(value="/list-todos",method=RequestMethod.GET)
	public String showTodoList(ModelMap model) {
		model.put("todos", service.retrieveTodos("FirstUser"));
		return "list-todos";
	}

	

}
