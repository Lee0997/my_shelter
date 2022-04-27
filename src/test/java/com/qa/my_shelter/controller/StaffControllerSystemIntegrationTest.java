package com.qa.my_shelter.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
public class StaffControllerSystemIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	StaffRepository staffRepository;

	private List<Staff> savedStaff;
	private List<StaffDTO> savedStaffDTOs = new ArrayList<>();
	private int nextId;
	private String uri;

	@BeforeEach
	public void init() {
		uri = "/staff";
		savedStaff = staffRepository.findAll();
		savedStaff.forEach(staff -> savedStaffDTOs.add(modelMapper.map(staff, StaffDTO.class)));
		nextId = savedStaff.get(savedStaff.size() - 1).getId() + 1;
	}

	@Test
	public void getAllStaffTest() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.request(HttpMethod.GET, uri);

		request.accept(MediaType.APPLICATION_JSON);

		String staff = objectMapper.writeValueAsString(savedStaffDTOs);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(staff);

		mockMvc.perform(request).andExpectAll(statusMatcher, contentMatcher);
	}

	@Test
	public void createStaffTest() throws Exception {
		NewStaffDTO newStaff = new NewStaffDTO();
		newStaff.setFirstName("John");
		newStaff.setSecondName("Carter");
		newStaff.setRole("Carer");
		StaffDTO expectedStaff = new StaffDTO(nextId, newStaff.getFirstName(), newStaff.getSecondName(),
				newStaff.getRole());

		var request = MockMvcRequestBuilders.request(HttpMethod.POST, uri);

		request.accept(MediaType.APPLICATION_JSON);

		request.content(objectMapper.writeValueAsString(newStaff));
		request.contentType(MediaType.APPLICATION_JSON);

		String expectedBody = objectMapper.writeValueAsString(expectedStaff);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher locationMatcher = MockMvcResultMatchers.header().string("Location",
				"http://localhost:8080/staff/" + expectedStaff.getId());
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(expectedBody);

		mockMvc.perform(request).andExpectAll(statusMatcher, locationMatcher, contentMatcher);

	}
}
