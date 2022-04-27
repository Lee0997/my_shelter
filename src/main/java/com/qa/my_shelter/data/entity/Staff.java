package com.qa.my_shelter.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "staff", targetEntity = Animal.class, fetch = FetchType.LAZY)
	private List<Animal> animals;

	@NotNull
	@NotBlank
	private String first_name;

	@NotNull
	@NotBlank
	private String second_name;

	@NotNull
	@NotBlank
	private String role;

	public Staff() {
		super();
	}

	public Staff(@NotNull @NotBlank String first_name, @NotNull @NotBlank String second_name,
			@NotNull @NotBlank String role) {
		super();
		this.first_name = first_name;
		this.second_name = second_name;
		this.role = role;
		this.animals = new ArrayList<>();
	}

	public Staff(int id, @NotNull @NotBlank String first_name, @NotNull @NotBlank String second_name,
			@NotNull @NotBlank String role) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.second_name = second_name;
		this.role = role;
		this.animals = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
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
		return Objects.hash(animals, first_name, id, role, second_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(animals, other.animals) && Objects.equals(first_name, other.first_name) && id == other.id
				&& Objects.equals(role, other.role) && Objects.equals(second_name, other.second_name);
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", animals=" + animals + ", first_name=" + first_name + ", second_name="
				+ second_name + ", role=" + role + "]";
	}

}
