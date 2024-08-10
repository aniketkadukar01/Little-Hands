package com.app.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentUpdateDto {

	@NotBlank(message = "Clinic name is mandatory!!!")
    private String clinicName;

    @NotNull(message = "Appointment date is Mandatory!!!")
    @FutureOrPresent(message = "Appointment date must be in the future or present")
    private LocalDate appointmentDate;

    private String reason;

    @NotNull(message = "Status is Mandatory!!!")
	@Enumerated(EnumType.STRING)
    private Status status;
    
}
