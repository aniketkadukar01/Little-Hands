package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class AppointmentDto {

	@NotNull(message = "Children details are mandatory!!!")
    private Long childrenId;

    @NotNull(message = "User Profile details are mandatory!!!")
    private Long userId;

    @NotNull(message = "Vaccine details are Mandatory!!!")
    private Long vaccineId;

    @NotBlank(message = "Clinic name is mandatory!!!")
    private String clinicName;

    @NotNull(message = "Appointment date is Mandatory!!!")
    @FutureOrPresent(message = "Appointment date must be in the future or present")
    private LocalDate appointmentDate;
    
    @NotNull(message = "Appointment Time is Mandatory!!!")
    private LocalTime appointmentTime;

    private String reason;

    @NotNull(message = "Status is Mandatory!!!")
	@Enumerated(EnumType.STRING)
    private Status status;
    
}
