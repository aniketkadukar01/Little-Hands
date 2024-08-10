package com.app.service;

import java.util.List;

import com.app.dto.AppointmentDto;
import com.app.dto.AppointmentUpdateDto;
import com.app.dto.ApiResponse;

public interface AppointmentService {

	AppointmentDto addNewAppointment(AppointmentDto appointmentDto);

    List<AppointmentDto> getAllAppointments();

    AppointmentUpdateDto updateAppointment(Long id, AppointmentUpdateDto appointmentUpdateDto);

    ApiResponse deleteAppointment(Long id);
    
}
