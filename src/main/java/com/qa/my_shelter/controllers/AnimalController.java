package com.qa.my_shelter.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.qa.my_shelter.service.*;
import com.qa.my_shelter.dto.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/animal")
public class AnimalController {

	private AnimalService animalService;

	@Autowired
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@GetMapping
	public ResponseEntity<List<AnimalDTO>> getAnimals() {
		return ResponseEntity.ok(animalService.getAnimals());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<AnimalDTO> getAnimal(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(animalService.getAnimal(id));
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<AnimalDTO> createAnimal(@Valid @RequestBody NewAnimalDTO animal, @PathVariable Integer id) {
		AnimalDTO newAnimal = animalService.createAnimal(animal, id != null ? id : 0);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/animal/" + newAnimal.getId());

		return new ResponseEntity<>(newAnimal, headers, HttpStatus.CREATED);
	}
	
	@PostMapping
	public ResponseEntity<AnimalDTO> createAnimal(@Valid @RequestBody NewAnimalDTO animal) {
		return createAnimal(animal, null);
	}
	

	@PutMapping(path = "/{id}")
	public ResponseEntity<AnimalDTO> updateAnimal(@Valid @RequestBody UpdateAnimalDTO animal,
			@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(animalService.updateAnimal(animal, id));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteAnimal(@PathVariable(name = "id") int id) {
		AnimalDTO deletedAnimal = animalService.getAnimal(id);
		animalService.deleteAnimal(id);
		return ResponseEntity.ok(deletedAnimal);
	}
}
