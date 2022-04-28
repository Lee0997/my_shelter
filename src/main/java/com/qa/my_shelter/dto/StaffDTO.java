package com.qa.my_shelter.dto;

import java.util.List;
import java.util.Objects;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.qa.my_shelter.data.entity.Animal;

public class StaffDTO {

	private int id;

	private String firstName;

	private String secondName;

	private String role;

	public StaffDTO() {
		super();
	}

	public StaffDTO(int id, String firstName, String secondName, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.role = role;
	}

	public StaffDTO(int id, String firstName, String secondName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, role, secondName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffDTO other = (StaffDTO) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(role, other.role)
				&& Objects.equals(secondName, other.secondName);
	}

	@Override
	public String toString() {
		return "StaffDTO [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", role=" + role
				+ "]";
	}

}
