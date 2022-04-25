package com.qa.my_shelter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.my_shelter.service.*;
import com.qa.my_shelter.dto.*;

@RestController
@RequestMapping(path = "/animal")
public class AnimalController {

	private AnimalService animalService;
	
	@Autowired
	public AnimalController(AnimalService animalservice) {
		this.animalService = animalService;
	}
	
	@GetMapping
	public ResponseEntity<List<AnimalDTO>> getAnimals() {
		return ResponseEntity.ok(animalService.getAnimals());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<AnimalDTO> getAnimal(@PathVariable(name = "id") int id) {
		AnimalDTO animal = animalService.getAnimal(id);
		return new ResponseEntity<>(animal, HttpStatus.OK);
	}
	
	
}
