package com.qa.my_shelter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.my_shelter.controllers.StaffController;
import com.qa.my_shelter.dto.NewStaffDTO;
import com.qa.my_shelter.dto.StaffDTO;
import com.qa.my_shelter.service.StaffService;

@WebMvcTest(StaffController.class)
@ActiveProfiles({ "test" })
public class StaffControllerWebLayerIntegrationTest {

	@MockBean
	private StaffService staffService;

	@Autowired
	private StaffController staffController;

	private List<StaffDTO> staffDTOs;

	@BeforeEach
	public void init() {
		staffDTOs = List.of(new StaffDTO(1, "John", "Testing"), new StaffDTO(2, "Sally", "OtherTesting"));
	}

	@Test
	public void getAllStaffTest() {
		ResponseEntity<?> expected = ResponseEntity.ok(staffDTOs);
		when(staffService.getStaff()).thenReturn(staffDTOs);

		ResponseEntity<?> actual = staffController.getStaff();

		assertEquals(expected, actual);
		verify(staffService).getStaff();
	}

	@Test
	public void createStaffTest() {
		NewStaffDTO newStaff = new NewStaffDTO();
		newStaff.setFirstName("Billy");
		newStaff.setSecondName("Bob");
		newStaff.setRole("Tester");

		StaffDTO expectedStaff = new StaffDTO(1, newStaff.getFirstName(), newStaff.getSecondName(), newStaff.getRole());
		ResponseEntity<?> expected = ResponseEntity
				.created(URI.create("http://localhost:8080/staff/" + expectedStaff.getId())).body(expectedStaff);
		
		when(staffService.createStaff(newStaff)).thenReturn(expectedStaff);
		
		ResponseEntity<?> actual = staffController.createStaff(newStaff);
		
		assertEquals(expected, actual);
		verify(staffService).createStaff(newStaff);
	}
}
