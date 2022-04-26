package com.qa.my_shelter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.transaction.Transactional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.my_shelter.data.entity.Staff;
import com.qa.my_shelter.data.repository.StaffRepository;
import com.qa.my_shelter.dto.NewStaffDTO;
import com.qa.my_shelter.dto.StaffDTO;

public class StaffService {

	private StaffRepository staffRepository;
	private AnimalService animalService;
	private ModelMapper modelmapper;

	@Autowired
	public StaffService(StaffRepository staffRepository, AnimalService animalService, ModelMapper modelMapper) {
		super();
		this.staffRepository = staffRepository;
		this.animalService = animalService;
		this.modelmapper = modelMapper;
	}

	public List<StaffDTO> getStaff() {
		List<Staff> staff = staffRepository.findAll();
		List<StaffDTO> dtos = new ArrayList<>();

		for (Staff staffMember : staff) {
			dtos.add(this.toDTO(staffMember));
		}
		return dtos;
	}

	private StaffDTO toDTO(Staff staffMember) {
		return this.modelmapper.map(staffMember, StaffDTO.class);
	}
	
	public StaffDTO getStaffById(int id) {
		Optional<Staff> staff = staffRepository.findById(id);
		
		if (staff.isPresent()) {
			return this.toDTO(staff.get());
		}
		throw new EntityNotFoundException("Staff member not found with id " + id);
	}

	public StaffDTO createStaff(NewStaffDTO staff) {
		Staff toSave = this.modelmapper.map(staff, Staff.class);
		Staff newStaff = staffRepository.save(toSave);
		return this.toDTO(newStaff);
	}

	@Transactional
	public StaffDTO updateStaff(NewStaffDTO staff, int id) {
		if (staffRepository.existsById(id)) {
			Staff savedStaff = staffRepository.getById(id);
			savedStaff.setFirst_name(staff.getFirstName());
			savedStaff.setSecond_name(staff.getSecondName);
			savedStaff.setRole(staff.getRole);
			savedStaff.setAnimal(staff.getAnimal);
			return this.toDTO(savedStaff);
		}
		throw new EntityNotFoundException("User not found with id " + id);
	}
	
	public void deleteStaff(int id) {
		if (staffRepository.existsById(id)) {
			staffRepository.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("Staff member not found with id " + id);
	}

}
