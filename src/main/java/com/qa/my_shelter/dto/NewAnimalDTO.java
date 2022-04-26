package com.qa.my_shelter.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewAnimalDTO {

	public NewAnimalDTO() {
		super();
	}
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 20, message = "The animal's name must have at least two characters and a maximum of twenty")
	private String name;
	
	@NotNull
	private String species;
	
	@NotNull
	private String gender;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpecies() {
		return species;
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gender, name, species);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewAnimalDTO other = (NewAnimalDTO) obj;
		return Objects.equals(gender, other.gender) && Objects.equals(name, other.name)
				&& Objects.equals(species, other.species);
	}

	public NewAnimalDTO(
			@NotNull @NotBlank @Size(min = 2, max = 20, message = "The animal's name must have at least two characters and a maximum of twenty") String name,
			@NotNull String species, @NotNull String gender) {
		super();
		this.name = name;
		this.species = species;
		this.gender = gender;
	}
	
}
