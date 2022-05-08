package com.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.web.model.Todo;
import com.springboot.web.service.TodoService;

@Controller
/*@SessionAttributes("name")*/
public class TodoController {
	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value="/list-todos",method=RequestMethod.GET)
	public String showTodoList(ModelMap model) {
		String name_s = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(name_s));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	
	@RequestMapping(value="/add-todo",method=RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo",new Todo(0, getLoggedInUserName(model), "", new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.POST)
	// @Valid to enable the validation on model
	// BindingResult contain the result "if the validation succeeded or not"
	public String AddTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()){
			return "todo"; // remain same page
		}
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		model.put("todos", service.retrieveTodos(getLoggedInUserName(model)));
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
		todo.setUser(getLoggedInUserName(model)); 
		service.updateTodo(todo);
		return "redirect:/list-todos";
	}

	

}
