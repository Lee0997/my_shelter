package com.qa.my_shelter.dto;

import java.util.Objects;

public class NewStaffDTO {

	private String firstName;

	private String secondName;

	private String role;

	public NewStaffDTO() {
		super();
	}

	public NewStaffDTO(String firstName, String secondName, String role) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.role = role;
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
		return Objects.hash(firstName, role, secondName);
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
		return Objects.equals(firstName, other.firstName) && Objects.equals(role, other.role)
				&& Objects.equals(secondName, other.secondName);
	}

	@Override
	public String toString() {
		return "NewStaffDTO [firstName=" + firstName + ", secondName=" + secondName + ", role=" + role + "]";
	}

}
