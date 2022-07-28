package com.lanaco.todo.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.lanaco.todo.dto.RegisterUserDto;
import com.lanaco.todo.dto.UserInfoDto;
import com.lanaco.todo.dto.UserUpdateDto;
import com.lanaco.todo.exception.RegistrationException;
import com.lanaco.todo.model.User;
import com.lanaco.todo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TodoService todoService;
	
	public User findById(Integer userId) throws NotFoundException{
		return userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("Nije pronadjen korisnik: "+userId));
	}
	
	public User save(RegisterUserDto userDto) throws RegistrationException {
		if(!userRepository.existsByEmail(userDto.getEmail())) {
			User user = userRepository.save(buildUserFromDto(userDto));
			return userRepository.save(user);
		}
		
		throw new RegistrationException("E-mail mora biti jedinstven!");
	}
	
	public List<UserInfoDto> findAll(){
		return userRepository.findAll().stream().map(e->buildUserInfoFromUser(e)).collect(Collectors.toList());
	}
	
	public User updateUser(UserUpdateDto userUpdateDto) throws javassist.NotFoundException{
		return userRepository.save(updateUserFromDto(userUpdateDto));
	}
	
	
	private User buildUserFromDto(RegisterUserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAge(userDto.getAge());
		
		return user;
	}
	
	private UserInfoDto buildUserInfoFromUser(User user) {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setAge(user.getAge());
		userInfoDto.setEmail(user.getEmail());
		userInfoDto.setPassword(user.getPassword());
		userInfoDto.setName(user.getName());
		return userInfoDto;
	}
	
	private User updateUserFromDto(UserUpdateDto userUpdateDto) throws javassist.NotFoundException{
		User user = findById(userUpdateDto.getUserId());
		user.setAge(Objects.isNull(userUpdateDto.getAge()) ? user.getAge() :userUpdateDto.getAge());
		user.setName(Objects.isNull(userUpdateDto.getName()) ? user.getName() :userUpdateDto.getName());
		user.setEmail(Objects.isNull(userUpdateDto.getEmail()) ? user.getEmail() :userUpdateDto.getEmail());
		user.setPassword(Objects.isNull(userUpdateDto.getPassword()) ? user.getPassword() :userUpdateDto.getPassword());
		return user;
	}
	
	@Transactional
	public User deleteById(Integer userId) throws NotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("Nije pronadjen korisnik: "+userId));
		todoService.deleteByUser(user);
		userRepository.deleteById(userId);
		return user;
	}
}
