package com.qa.my_shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.my_shelter.data.entity.Animal;
import com.qa.my_shelter.data.entity.Staff;
import com.qa.my_shelter.data.repository.AnimalRepository;
import com.qa.my_shelter.data.repository.StaffRepository;
import com.qa.my_shelter.dto.AnimalDTO;
import com.qa.my_shelter.dto.NewAnimalDTO;
import com.qa.my_shelter.dto.UpdateAnimalDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;

@Service
public class AnimalService {

	private AnimalRepository animalRepository;
	private StaffRepository staffRepository;
	private ModelMapper modelMapper;

	@Autowired
	public AnimalService(AnimalRepository animalRepository, ModelMapper modelMapper, StaffRepository staffRepository) {
		super();
		this.animalRepository = animalRepository;
		this.modelMapper = modelMapper;
		this.staffRepository = staffRepository;
	}

	public List<AnimalDTO> getAnimals() {
		List<Animal> animals = animalRepository.findAll();
		List<AnimalDTO> dtos = new ArrayList<>();

		for (Animal animal : animals) {
			dtos.add(this.toDTO(animal));
		}
		return dtos;
	}

	public List<AnimalDTO> getAnimalsByCarer(int id) {
		List<Animal> animals = animalRepository.findByStaffId(id);
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
		throw new EntityNotFoundException("Animal not found with this id " + id);
	}

	public AnimalDTO createAnimal(NewAnimalDTO animal, int staffId) {
		Animal toSave = this.modelMapper.map(animal, Animal.class);
		if (staffRepository.existsById(staffId)) toSave.setStaff(staffRepository.getById(staffId));
		Animal newAnimal = animalRepository.save(toSave);
		return this.toDTO(newAnimal);
	}

	@Transactional
	public AnimalDTO updateAnimal(NewAnimalDTO animal, int id) {
		if (animalRepository.existsById(id)) {
			Animal savedAnimal = animalRepository.getById(id);
			savedAnimal.setName(animal.getName());
			savedAnimal.setGender(animal.getGender());
			return this.toDTO(savedAnimal);
		}
		throw new EntityNotFoundException("Animal not found with id " + id);
	}

	public void deleteAnimal(int id) {
		if (animalRepository.existsById(id)) {
			animalRepository.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("Animal not found with id " + id);
	}

	public AnimalDTO updateAnimal(@Valid UpdateAnimalDTO animalDTO, int id) {
		if (animalRepository.existsById(id)) {
			Animal savedAnimal = animalRepository.getById(id);
			savedAnimal.setName(animalDTO.getName());
			savedAnimal.setSpecies(animalDTO.getSpecies());
			savedAnimal.setGender(animalDTO.getGender());
			return this.toDTO(animalRepository.save(savedAnimal));
		}
		throw new EntityNotFoundException("Animal not found with id " + id);
	}

}
