package com.qa.my_shelter.data.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "staff")
public class Staff {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id", referencedColumnName = "id")
	private Animal animal;
	
	@NotNull
	@NotBlank
	private String first_name;
	
	@NotNull
	@NotBlank
	private String second_name;
	
	@NotNull
	@NotBlank
	private String role;

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Staff(Animal animal, @NotNull @NotBlank String first_name, @NotNull @NotBlank String second_name,
			@NotNull @NotBlank String role) {
		super();
		this.animal = animal;
		this.first_name = first_name;
		this.second_name = second_name;
		this.role = role;
	}
	
}
