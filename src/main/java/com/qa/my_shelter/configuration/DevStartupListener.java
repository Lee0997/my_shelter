package com.qa.my_shelter.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.my_shelter.data.entity.Animal;
import com.qa.my_shelter.data.repository.AnimalRepository;

@Profile("dev")
@Configuration
public class DevStartupListener {

	private AnimalRepository animalRepository;
	
	@Autowired
	public DevStartupListener(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}
	
	public void onApplicationEvent(ApplicationReadyEvent event) {
		List<Animal> animals = animalRepository.saveAll(List.of(
				new Animal("Bubbles", "fish", "male")
				));
		Animal animal = animals.stream().filter(a -> a.getSpecies().equals("fish")).findFirst().orElse(null);
	}
}
