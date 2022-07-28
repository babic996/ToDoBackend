package com.lanaco.todo.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanaco.todo.dto.TodoCreateDto;
import com.lanaco.todo.dto.TodoInfoDto;
import com.lanaco.todo.dto.TodoUpdateDto;
import com.lanaco.todo.model.Todo;
import com.lanaco.todo.model.User;
import com.lanaco.todo.repository.TodoRepository;
import com.lanaco.todo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class TodoService {
	
	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Todo findById(Integer todoId) throws NotFoundException{
		return todoRepository.findById(todoId)
				.orElseThrow(() -> new NotFoundException("Nije pronadjena obaveza: "+todoId));
	}
	
	public List<Todo> deleteByUser(User user){
		return todoRepository.deleteByUser(user);
	}
	
	public Todo save(TodoCreateDto createDto) throws javassist.NotFoundException {
		return todoRepository.save(buildTodoFromDto(createDto));
	}
	
	public Todo update(TodoUpdateDto updateDto, Integer id) throws javassist.NotFoundException{
		return todoRepository.save(updateTodoFromDto(updateDto, id));
	}
	
	public List<TodoInfoDto> findAll(){
		return todoRepository.findAll().stream().map(e->buildTodoInfoFromTodo(e)).collect(Collectors.toList());
	}
	
	public Todo deleteById(Integer todoId) throws NotFoundException{
		Todo todo = todoRepository.findById(todoId).orElseThrow(
				() -> new NotFoundException("Nije pronađena obaveza u tabeli Todo (id:" + todoId + ")!"));
		todoRepository.deleteById(todoId);
		return todo;
	}
	
	
	private Todo buildTodoFromDto(TodoCreateDto createDto) throws javassist.NotFoundException {
		Todo todo = new Todo();
		
		todo.setDescription(createDto.getDescription());
		User user = userRepository.findById(createDto.getUserId()).orElseThrow(() -> new NotFoundException("Nije pronađen korisnik! (id:"+createDto.getUserId()+")"));
		todo.setUser(user);
		return todo;
	}
	
	private Todo updateTodoFromDto(TodoUpdateDto updateDto, Integer id) throws javassist.NotFoundException{
		Todo todo = findById(id);
		todo.setChecked(Objects.isNull(updateDto.getChecked()) ? todo.getChecked() :updateDto.getChecked());
		todo.setDescription(Objects.isNull(updateDto.getDescription()) ? todo.getDescription() : updateDto.getDescription());
		return todo;
	}
	
	private TodoInfoDto buildTodoInfoFromTodo(Todo todo) {
		TodoInfoDto todoInfoDto = new TodoInfoDto();
		todoInfoDto.setChecked(todo.getChecked());
		todoInfoDto.setDescription(todo.getDescription());
		return todoInfoDto;
	}


}
