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
	private String firstName;

	@NotNull
	@NotBlank
	private String secondName;

	@Column(name = "_role")
	@NotNull
	@NotBlank
	private String role;

	public Staff() {
		super();
	}

	public Staff(@NotNull @NotBlank String firstName, @NotNull @NotBlank String secondName,
			@NotNull @NotBlank String role) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.role = role;
		this.animals = new ArrayList<>();
	}

	public Staff(int id, @NotNull @NotBlank String firstName, @NotNull @NotBlank String secondName,
			@NotNull @NotBlank String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
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
		return Objects.hash(animals, firstName, id, role, secondName);
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
		return Objects.equals(animals, other.animals) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(role, other.role) && Objects.equals(secondName, other.secondName);
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", animals=" + animals + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", role=" + role + "]";
	}

}
