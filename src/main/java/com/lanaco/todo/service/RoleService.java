package com.lanaco.todo.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanaco.todo.dto.RoleCreateDto;
import com.lanaco.todo.dto.RoleInfoDto;
import com.lanaco.todo.dto.RoleUpdateDto;
import com.lanaco.todo.model.Role;
import com.lanaco.todo.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public Role findById(Integer roleId) throws javassist.NotFoundException{
		return roleRepository.findById(roleId)
				.orElseThrow(()->new javassist.NotFoundException("Nije pronadjena rola sa id: "+roleId));
	}
	
	public Role save(RoleCreateDto roleCreateDto) throws javassist.NotFoundException{
		return roleRepository.save(buildRoleFromDto(roleCreateDto));
	}
	
	public Role update(RoleUpdateDto updateDto) throws javassist.NotFoundException{
		return roleRepository.save(updateRoleFromDto(updateDto));
	}
	
	public List<RoleInfoDto> findAll(){
		return roleRepository.findAll().stream().map(e->infoRoleFromDto(e)).collect(Collectors.toList());
	}
	
	
	private Role buildRoleFromDto(RoleCreateDto roleCreateDto) throws javassist.NotFoundException{
		Role role = new Role();
		
		role.setRoleName(roleCreateDto.getRoleName());
		return role;
	}
	
	private Role updateRoleFromDto(RoleUpdateDto roleUpdateDto) throws javassist.NotFoundException{
		Role role = findById(roleUpdateDto.getRoleId());
		role.setRoleName(Objects.isNull(roleUpdateDto.getRoleName()) ? role.getRoleName() :roleUpdateDto.getRoleName());
		return role;
	}
	
	private RoleInfoDto infoRoleFromDto(Role role) {
		RoleInfoDto roleInfoDto = new RoleInfoDto();
		roleInfoDto.setRoleName(role.getRoleName());
		return roleInfoDto;
	}

}
