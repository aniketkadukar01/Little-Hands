package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.AccessDeniedException;
import com.app.custom_exception.ArgumentNotFound;
import com.app.dto.ApiResponse;
import com.app.dto.AppointmentDto;
import com.app.dto.AppointmentUpdateDto;
import com.app.entity.Appointment;
import com.app.entity.Children;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.entity.Vaccine;
import com.app.repository.AppointmentRepository;
import com.app.repository.ChildrenRepository;
import com.app.repository.UserRepository;
import com.app.repository.VaccineRepository;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ChildrenRepository childrenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VaccineRepository vaccineRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AppointmentDto addNewAppointment(AppointmentDto appointmentDto) {
		
		User user = userRepository.findById(appointmentDto.getUserId())
				.orElseThrow(() -> new ArgumentNotFound("User Profile ID is Invalid!!!"));
		
		// Check if the user has the role 'ROLE_PARENT'
	    if (!user.getRole().equals(Role.ROLE_PARENT)) {
	        throw new AccessDeniedException("Only users with 'PARENT' role can add appointments.");
	    }
		
		
		Children children = childrenRepository.findById(appointmentDto.getChildrenId())
				.orElseThrow(() -> new ArgumentNotFound("Children ID is Invalid!!!"));
		
	    
		Vaccine vaccine = vaccineRepository.findById(appointmentDto.getVaccineId())
				.orElseThrow(() -> new ArgumentNotFound("Vaccine ID is Invalid!!!"));

		Appointment appointment = mapper.map(appointmentDto, Appointment.class);
		appointment.setChildren(children);
		appointment.setUser(user);
		appointment.setVaccine(vaccine);

		appointmentRepository.save(appointment);
		return mapper.map(appointment, AppointmentDto.class);
	}

	@Override
	public List<AppointmentDto> getAllAppointments() {
		return appointmentRepository.findAll().stream()
				.map(appointment -> mapper.map(appointment, AppointmentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public AppointmentUpdateDto updateAppointment(Long id, AppointmentUpdateDto appointmentUpdateDto) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ArgumentNotFound("Appointment ID is Invalid!!!"));

		mapper.map(appointmentUpdateDto, appointment);
		
		appointmentRepository.save(appointment);
		return mapper.map(appointment, AppointmentUpdateDto.class);
	}

	@Override
	public ApiResponse deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
		return new ApiResponse("Appointment is deleted successfully with id " + id);
	}

}
