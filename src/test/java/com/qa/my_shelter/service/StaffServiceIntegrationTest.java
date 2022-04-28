package com.qa.my_shelter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.my_shelter.data.entity.Staff;
import com.qa.my_shelter.data.repository.StaffRepository;
import com.qa.my_shelter.dto.NewStaffDTO;
import com.qa.my_shelter.dto.StaffDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({ "test" })
@Sql(scripts = { "classpath:schema.sql",
		"classpath:staff-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StaffServiceIntegrationTest {

	@Autowired
	private StaffService staffService;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private ModelMapper modelMapper;

	private List<Staff> savedStaff;
	private List<StaffDTO> savedStaffDTO = new ArrayList<>();
	private int nextId;

	@BeforeEach
	public void init() {
		savedStaff = staffRepository.findAll();
		savedStaff.forEach(staff -> savedStaffDTO.add(modelMapper.map(staff, StaffDTO.class)));

		nextId = savedStaff.get(savedStaff.size() - 1).getId() + 1;
	}

	@Test
	public void getAllStaffTest() {
		assertEquals(savedStaffDTO, staffService.getStaff());
	}

	@Test
	public void getStaffByIdTest() {
		StaffDTO expected = savedStaffDTO.get(0);
		StaffDTO actual = staffService.getStaffById(expected.getId());
		assertEquals(expected, actual);
	}

	@Test
	public void createStaffTest() {
		NewStaffDTO staff = new NewStaffDTO();
		staff.setFirstName("Testy");
		staff.setSecondName("Testing");
		staff.setRole("Tester");

		StaffDTO expected = new StaffDTO(nextId, staff.getFirstName(), staff.getSecondName(), staff.getRole());
		StaffDTO actual = staffService.createStaff(staff);

		assertEquals(expected, actual);
	}

	@Test
	public void updateUserTest() {
		int id = savedStaff.get(0).getId();
		NewStaffDTO staff = new NewStaffDTO();
		staff.setFirstName("John");
		staff.setSecondName("Jimmy");
		staff.setRole("Update Tester");

		StaffDTO expected = new StaffDTO(id, staff.getFirstName(), staff.getSecondName(), staff.getRole());
		StaffDTO actual = staffService.updateStaff(staff, id);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteStaffTest() {
		int id = savedStaff.get(0).getId();
		staffService.deleteStaff(id);
		assertEquals(Optional.empty(), staffRepository.findById(id));
	}

}
