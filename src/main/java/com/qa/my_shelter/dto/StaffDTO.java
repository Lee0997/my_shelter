package com.qa.my_shelter.dto;

import java.util.Objects;

public class StaffDTO {

	private int id;

	private String first_name;

	private String second_name;

	private String role;

	public StaffDTO() {
		super();
	}

	public StaffDTO(int id, String first_name, String second_name, String role) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.second_name = second_name;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(first_name, id, role, second_name);
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
		return Objects.equals(first_name, other.first_name) && id == other.id && Objects.equals(role, other.role)
				&& Objects.equals(second_name, other.second_name);
	}

	@Override
	public String toString() {
		return "StaffDTO [id=" + id + ", first_name=" + first_name + ", second_name=" + second_name + ", role=" + role
				+ "]";
	}

}
