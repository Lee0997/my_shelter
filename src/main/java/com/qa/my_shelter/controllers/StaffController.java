package com.qa.my_shelter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.my_shelter.dto.StaffDTO;
import com.qa.my_shelter.service.StaffService;

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
	
}
