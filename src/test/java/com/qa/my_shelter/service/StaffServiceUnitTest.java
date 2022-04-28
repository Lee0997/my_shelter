package com.qa.my_shelter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.my_shelter.data.entity.Staff;
import com.qa.my_shelter.data.repository.StaffRepository;
import com.qa.my_shelter.dto.NewStaffDTO;
import com.qa.my_shelter.dto.StaffDTO;

@ExtendWith(MockitoExtension.class)
public class StaffServiceUnitTest {

	@Mock
	private StaffRepository staffRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private StaffService staffService;

	private List<Staff> allStaff;
	private List<StaffDTO> staffDTOs;

	@BeforeEach
	public void init() {
		allStaff = List.of(new Staff(1, "John", "Carter", "Tester"), new Staff(2, "Sally", "Smith", "Receptionist"));
		staffDTOs = List.of(new StaffDTO(1, "John", "Carter", "Tester"), new StaffDTO(2, "Sally", "Smith", "Receptionist"));
	}

	@Test
	public void getAllTest() {
		when(staffRepository.findAll()).thenReturn(allStaff);
		when(modelMapper.map(allStaff.get(0), StaffDTO.class)).thenReturn(staffDTOs.get(0));
		when(modelMapper.map(allStaff.get(1), StaffDTO.class)).thenReturn(staffDTOs.get(1));

		List<StaffDTO> actual = staffService.getStaff();

		assertEquals(staffDTOs, actual);
		verify(staffRepository).findAll();
		verify(modelMapper).map(allStaff.get(0), StaffDTO.class);
		verify(modelMapper).map(allStaff.get(1), StaffDTO.class);
	}

	@Test
	public void getByIdTest() {
		Staff staff = allStaff.get(0);
		StaffDTO staffDTO = staffDTOs.get(0);
		int id = staff.getId();

		when(staffRepository.findById(id)).thenReturn(Optional.of(staff));
		when(modelMapper.map(staffDTO, StaffDTO.class)).thenReturn(staffDTO);

		StaffDTO actual = staffService.getStaffById(id);

		assertEquals(staffDTO, actual);
		verify(staffRepository).findById(id);
		verify(modelMapper).map(staff, StaffDTO.class);
	}

	@Test
	public void getByInvalidIdTest() {
		int id = 152314;
		when(staffRepository.findById(id)).thenReturn(Optional.empty());

		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			staffService.getStaffById(id);
		});
		String expectedMessage = "Staff member not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(staffRepository).findById(id);
	}

	@Test
	public void createTest() {
		Staff staff = allStaff.get(0);

		NewStaffDTO staffDTO = new NewStaffDTO();
		staffDTO.setFirstName(staff.getFirstName());
		staffDTO.setSecondName(staff.getSecondName());

		StaffDTO newStaff = new StaffDTO(staff.getId(), staff.getFirstName(), staff.getSecondName());

		when(modelMapper.map(staffDTO, Staff.class)).thenReturn(staff);
		when(staffRepository.save(staff)).thenReturn(staff);
		when(modelMapper.map(staff, StaffDTO.class)).thenReturn(newStaff);
	}
}
