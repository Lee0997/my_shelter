package com.qa.my_shelter.data.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id", referencedColumnName = "id")
	private Animal animal;

	@ManyToMany
	@JoinTable(name = "animal_staff", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "staff_id"))
	Set<Animal> assignedAnimals;

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

	public Staff(int id, @NotNull @NotBlank String first_name, @NotNull @NotBlank String second_name,
			@NotNull @NotBlank String role) {
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
		Staff other = (Staff) obj;
		return Objects.equals(first_name, other.first_name) && id == other.id && Objects.equals(role, other.role)
				&& Objects.equals(second_name, other.second_name);
	}

}
