package com.qa.my_shelter.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.qa.my_shelter.dto.AnimalDTO;
import com.qa.my_shelter.dto.NewStaffDTO;
import com.qa.my_shelter.dto.StaffDTO;
import com.qa.my_shelter.service.StaffService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/staff")
public class StaffController {
	
	private StaffService staffService;
	
	@Autowired
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@GetMapping
	public ResponseEntity<List<StaffDTO>> getStaff() {
		return ResponseEntity.ok(staffService.getStaff());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<StaffDTO> getStaff(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(staffService.getStaffById(id));
	}
	
	@GetMapping(path = "/{id}/animals")
	public ResponseEntity<List<AnimalDTO>> getAnimalsByCarer(@PathVariable(name = "id") int staffId) {
		return ResponseEntity.ok(staffService.getAnimalsByCarer(staffId));
	}
	
	@PostMapping
	public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody NewStaffDTO staff) {
		StaffDTO newStaff = staffService.createStaff(staff);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/staff/" + newStaff.getId());
		
		return new ResponseEntity<>(newStaff, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<StaffDTO> updateStaff(@RequestBody NewStaffDTO newStaffDTO, @PathVariable(name = "id") int id) {
		return ResponseEntity.ok(staffService.updateStaff(newStaffDTO, id));
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteStaff(@PathVariable(name = "id") int id) {
		StaffDTO deletedStaff = staffService.getStaffById(id);
		staffService.deleteStaff(id);
		return ResponseEntity.ok(deletedStaff);
	}
}
