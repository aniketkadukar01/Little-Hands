package com.app.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	boolean existsByAppointmentDateAndAppointmentTimeBetween(LocalDate appointmentDate, LocalTime startTime, LocalTime endTime);
}
