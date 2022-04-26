package com.qa.my_shelter.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnimalDTO {

	private int id;
	
	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String species;
	
	@NotNull
	@NotBlank
	private String gender;
	
	public AnimalDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gender, id, name, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalDTO other = (AnimalDTO) obj;
		return Objects.equals(gender, other.gender) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(species, other.species);
	}

	public AnimalDTO(int id, @NotNull @NotBlank String name, @NotNull @NotBlank String species,
			@NotNull @NotBlank String gender) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}
