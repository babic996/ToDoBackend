package com.lanaco.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="todo")

public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_todo_seq")
	@SequenceGenerator(name = "gen_todo_seq", allocationSize = 1, sequenceName = "gen_todo_seq")
	private int todoId;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="checked", nullable=false)
	private Boolean checked=false;
	
	@Column(name="created_at", nullable=false)
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

}
