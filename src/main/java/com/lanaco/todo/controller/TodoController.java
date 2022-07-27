package com.lanaco.todo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanaco.todo.dto.TodoCreateDto;
import com.lanaco.todo.dto.TodoInfoDto;
import com.lanaco.todo.dto.TodoUpdateDto;
import com.lanaco.todo.model.Todo;
import com.lanaco.todo.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@PostMapping("/create")
	public Todo save(@RequestBody TodoCreateDto createDto) throws javassist.NotFoundException {
		return todoService.save(createDto);
	}
	
	
	@PutMapping("/update/{id}")
	public Todo update(@RequestBody TodoUpdateDto todoUpdateDto, @PathVariable Integer id) throws javassist.NotFoundException
	{
		return todoService.update(todoUpdateDto, id);
	}
	
	@DeleteMapping("/delete")
	public Todo delete(@RequestParam int todoId) throws javassist.NotFoundException{
		return todoService.deleteById(todoId);
	}
	
	@GetMapping("/find-all")
	public List<TodoInfoDto> findAll(){
		return todoService.findAll();
	}
}
