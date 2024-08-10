package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.AppointmentDto;
import com.app.dto.AppointmentUpdateDto;
import com.app.service.AppointmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	
	@Autowired
    private AppointmentService appointmentService;

    @PostMapping("/addappointment")
    public ResponseEntity<?> addNewAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService.addNewAppointment(appointmentDto));
    }

    @GetMapping("/getallappointments")
    public ResponseEntity<?> getAllAppointments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService.getAllAppointments());
    }

    @PutMapping("/updateappointment/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id,
                                                @Valid @RequestBody AppointmentUpdateDto appointmentUpdateDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService.updateAppointment(id, appointmentUpdateDto));
    }

    @DeleteMapping("/deleteappointment/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService.deleteAppointment(id));
    }
}
