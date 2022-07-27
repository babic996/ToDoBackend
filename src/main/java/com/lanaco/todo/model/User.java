package com.lanaco.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="application_user")


public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_user_seq")
	@SequenceGenerator(name = "gen_user_seq", allocationSize = 1, sequenceName = "gen_user_seq")
	private int userId;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="age", nullable=false)
	private int age;
	

}
