package com.lanaco.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoUpdateDto {
	private int todoId;
	private String description;
	private Boolean checked;
}
