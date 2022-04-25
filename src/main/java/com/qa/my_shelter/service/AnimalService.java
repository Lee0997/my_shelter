package com.qa.my_shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.my_shelter.data.repository.AnimalRepository;

import org.modelmapper.ModelMapper;

@Service
public class AnimalService {

	private AnimalRepository animalRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public AnimalService(AnimalRepository animalRepository, ModelMapper modelMapper) {
		super();
		this.animalRepository = animalRepository;
		this.modelMapper = modelMapper;
	}
}
