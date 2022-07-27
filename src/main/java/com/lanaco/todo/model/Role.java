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
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="gen_role_seq")
	@SequenceGenerator(name="gen_role_seq", allocationSize=1, sequenceName="gen_role_seq")
	private int roleId;
	
	@Column(name="role_name", nullable=false)
	private String roleName;
}
