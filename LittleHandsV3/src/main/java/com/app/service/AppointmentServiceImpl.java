package com.app.service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.AccessDeniedException;
import com.app.custom_exception.ArgumentNotFound;
import com.app.custom_exception.InvalidTimeSlotException;
import com.app.custom_exception.SlotAlreadyBookedException;
import com.app.dto.ApiResponse;
import com.app.dto.AppointmentDto;
import com.app.dto.AppointmentResponseDto;
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
	public AppointmentResponseDto addNewAppointment(AppointmentDto appointmentDto) {
		
		User user = userRepository.findById(appointmentDto.getUserId())
				.orElseThrow(() -> new ArgumentNotFound("User Profile ID is Invalid!!!"));
		
		// Check if the user has the role 'ROLE_PARENT'
	    if (!user.getRole().equals(Role.ROLE_PARENT)) {
	        throw new AccessDeniedException("Only users with 'PARENT' role can add appointments.");
	    }
		
		// Ensure the time is between 9 AM and 5 PM
	    LocalTime appointmentTime = appointmentDto.getAppointmentTime();
	    LocalTime startTime = LocalTime.of(9, 0);  // 9:00 AM
	    LocalTime endTime = LocalTime.of(17, 0);   // 5:00 PM
	    
	    
	    if (appointmentTime.isBefore(startTime) || appointmentTime.isAfter(endTime.minusMinutes(15))) {
	        throw new InvalidTimeSlotException("Appointment time must be between 9 AM and 4:45 PM to accommodate a 15-minute slot.");
	    }
	    
	 // Calculate the end time of the appointment (15 minutes after the start time)
	    LocalTime appointmentEndTime = appointmentTime.plusMinutes(15);
	    
	 // Check if the slot is already taken for the given date and time range
	    boolean isSlotTaken = appointmentRepository.existsByAppointmentDateAndAppointmentTimeBetween(
	            appointmentDto.getAppointmentDate(), 
	            appointmentTime.minusMinutes(14), 
	            appointmentEndTime);
	    
	    if (isSlotTaken) {
	        throw new SlotAlreadyBookedException("This slot is already booked. Please choose another time.");
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
		return mapper.map(appointment, AppointmentResponseDto.class);
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
