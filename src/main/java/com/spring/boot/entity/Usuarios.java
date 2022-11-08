package com.spring.boot.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	@ManyToMany
	@JoinTable(name="usuarios_roles",
	joinColumns=@JoinColumn(name="id_username"),
	inverseJoinColumns = @JoinColumn(name="id_roles"))
	private Set<Roles> roles = new HashSet<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
