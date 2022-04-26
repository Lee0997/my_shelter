package com.qa.my_shelter.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.my_shelter.dto.NewStaffDTO;
import com.qa.my_shelter.dto.StaffDTO;
import com.qa.my_shelter.service.StaffService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/post")
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
	
	@PostMapping
	public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody NewStaffDTO staff) {
		StaffDTO newStaff = staffService.createStaff(staff);
	}
}
