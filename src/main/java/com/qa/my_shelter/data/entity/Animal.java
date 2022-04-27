package com.qa.my_shelter.data.entity;

import java.sql.Date;
import java.time.LocalDate;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "animal")
public class Animal {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotBlank
	@Size(min = 2, max = 20, message = "The animal's name must have at least two characters and a maximum of twenty")
	private String name;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id", referencedColumnName = "id")
	private Staff staff;

	@NotNull
	private String species;

	@NotNull
	private String gender;

	@NotNull
	private LocalDate entry_date;

	public Animal() {
		super();
		entry_date = LocalDate.now();
	}

	public Animal(int id,
			@NotNull @NotBlank @Size(min = 2, max = 20, message = "The animal's name must have at least two characters and a maximum of twenty") String name,
			@NotNull String species, @NotNull String gender, @NotNull LocalDate entry_date) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.gender = gender;
		this.entry_date = entry_date;
	}

	public int getId() {
		return id;
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

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
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

	public LocalDate getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(LocalDate entry_date) {
		this.entry_date = entry_date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(entry_date, gender, id, name, species, staff);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(entry_date, other.entry_date) && Objects.equals(gender, other.gender) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(species, other.species)
				&& Objects.equals(staff, other.staff);
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", staff=" + staff + ", species=" + species + ", gender="
				+ gender + ", entry_date=" + entry_date + "]";
	}

}
