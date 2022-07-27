package com.lanaco.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto {
	private int userId;
	private String name;
	private String email;
	private String password;
	private Integer age;
}
