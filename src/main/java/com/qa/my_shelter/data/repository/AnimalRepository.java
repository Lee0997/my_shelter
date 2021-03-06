package com.qa.my_shelter.data.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qa.my_shelter.data.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	List<Animal> findByStaffId(int id);
}
