package com.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name ="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "O campo deve ser preenchido")
	@Size(message = "O cpf deve ser preenchido")
	private String cpf;
	@NotNull(message = "O campo deve ser preenchido")
	@Size(min = 3, max = 12, message = "O login deve ter no minimo 3 caracteres e no maximo 12")
	private String username;
	@NotNull(message = "O campo deve ser preenchido")
	private String password;
	@NotNull(message = "O campo deve ser preenchido")
	private Double salary;
	@NotNull(message = "O campo deve ser preenchido")
	@Size(min = 3, max = 60, message = "O nome deve ter no minimo 3 caracter e no maximo 60 ")
	private String name;
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "roles_id" , referencedColumnName = "id"))
	private List<Role> roles;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
