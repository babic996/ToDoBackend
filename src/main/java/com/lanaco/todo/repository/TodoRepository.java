package com.lanaco.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lanaco.todo.model.Todo;
import com.lanaco.todo.model.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> deleteByUser(User user);

}
