package com.qa.my_shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.my_shelter.data.entity.Animal;
import com.qa.my_shelter.data.repository.AnimalRepository;
import com.qa.my_shelter.dto.AnimalDTO;
import com.qa.my_shelter.dto.NewAnimalDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
	
	public List<AnimalDTO> getAnimals() {
		List<Animal> animals = animalRepository.findAll();
		List<AnimalDTO> dtos = new ArrayList<>();
		
		for (Animal animal : animals) {
			dtos.add(this.toDTO(animal));
		}
		return dtos;
	}

	private AnimalDTO toDTO(Animal animal) {
		return this.modelMapper.map(animal, AnimalDTO.class);
	}
	
	public AnimalDTO getAnimal(int id) {
		Optional<Animal> animal = animalRepository.findById(id);
		
		if (animal.isPresent()) {
			return this.toDTO(animal.get());
		}
		throw new EntityNotFoundException("Animal not found with id " + id);
	}
	
	public AnimalDTO createAnimal(NewAnimalDTO animal) {
		Animal toSave = this.modelMapper.map(animal, Animal.class);
		Animal newAnimal = animalRepository.save(toSave);
		return this.toDTO(newAnimal);
	}
	
	
}
