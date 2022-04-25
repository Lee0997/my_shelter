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
}
