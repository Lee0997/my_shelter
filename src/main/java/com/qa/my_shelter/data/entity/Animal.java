package com.qa.my_shelter.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@NotNull
	private String species;
	
	@NotNull
	private String gender;
	
	@NotNull
	private Date entry_date;

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

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Animal(int id,
			@NotNull @NotBlank @Size(min = 2, max = 20, message = "The animal's name must have at least two characters and a maximum of twenty") String name,
			@NotNull String species, @NotNull String gender, @NotNull Date entry_date) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.gender = gender;
		this.entry_date = entry_date;
	}
	
	
}
