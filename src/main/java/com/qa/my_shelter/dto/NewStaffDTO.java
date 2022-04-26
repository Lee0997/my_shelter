package com.qa.my_shelter.dto;

import java.util.Objects;

public class NewStaffDTO {

	public NewStaffDTO() {
		super();
	}
	
	private String first_name;
	
	private String second_name;
	
	private String role;
	
	private AnimalDTO animalDTO;

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

	public AnimalDTO getAnimalDTO() {
		return animalDTO;
	}

	public void setAnimalDTO(AnimalDTO animalDTO) {
		this.animalDTO = animalDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animalDTO, first_name, role, second_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewStaffDTO other = (NewStaffDTO) obj;
		return Objects.equals(animalDTO, other.animalDTO) && Objects.equals(first_name, other.first_name)
				&& Objects.equals(role, other.role) && Objects.equals(second_name, other.second_name);
	}

	public NewStaffDTO(String first_name, String second_name, String role, AnimalDTO animalDTO) {
		super();
		this.first_name = first_name;
		this.second_name = second_name;
		this.role = role;
		this.animalDTO = animalDTO;
	}
	
}
