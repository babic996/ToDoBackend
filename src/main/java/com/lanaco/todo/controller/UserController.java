package com.lanaco.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanaco.todo.dto.RegisterUserDto;
import com.lanaco.todo.dto.UserInfoDto;
import com.lanaco.todo.dto.UserUpdateDto;
import com.lanaco.todo.exception.RegistrationException;
import com.lanaco.todo.model.User;
import com.lanaco.todo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public User save(@RequestBody RegisterUserDto registerUserDto) throws RegistrationException {
		return userService.save(registerUserDto);
	}
	
	@PutMapping("/update")
	public User update(@RequestBody UserUpdateDto userUpdateDto) throws javassist.NotFoundException{
		return userService.updateUser(userUpdateDto);
	}
	
	@DeleteMapping("/delete")
	public User delete(@RequestParam Integer userId) throws javassist.NotFoundException{
		return userService.deleteById(userId);
	}
	
	@GetMapping("/find-all")
	public List<UserInfoDto> findAll() {
		return userService.findAll();
	}

}
