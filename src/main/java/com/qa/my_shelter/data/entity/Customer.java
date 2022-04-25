package com.qa.my_shelter.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class Customer {

	@NotNull
	@NotBlank
	private String first_name;
	
	@NotNull
	@NotBlank
	private String second_name;
	
	@NotNull
	@NotBlank
	private String address;
	
	@NotNull
	@NotBlank
	private int phone_number;
	
	@Email
	private String email;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(@NotNull @NotBlank String first_name, @NotNull @NotBlank String second_name,
			@NotNull @NotBlank String address, @NotNull @NotBlank int phone_number, @Email String email) {
		super();
		this.first_name = first_name;
		this.second_name = second_name;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
	}
}
