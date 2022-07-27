package com.lanaco.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanaco.todo.dto.RoleCreateDto;
import com.lanaco.todo.dto.RoleInfoDto;
import com.lanaco.todo.dto.RoleUpdateDto;
import com.lanaco.todo.model.Role;
import com.lanaco.todo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/create")
	public Role save(@RequestBody RoleCreateDto createDto) throws javassist.NotFoundException{
		return roleService.save(createDto);
	}
	
	@PutMapping("/update")
	public Role update(@RequestBody RoleUpdateDto roleUpdateDto) throws javassist.NotFoundException{
		return roleService.update(roleUpdateDto);
	}
	
	@GetMapping("/find-all")
	public List<RoleInfoDto> findAll(){
		return roleService.findAll();
	}

}
