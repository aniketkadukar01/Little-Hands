package com.app.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.app.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateChildrenDto {

	@NotBlank(message = "Please enter your first name!!!")
	private String firstName;

	@NotBlank(message = "Please enter your last name!!!")
	private String lastName;

	@NotNull(message = "Date of Birth is Mandatory!!!")
	@Past(message = "Date must be in past!!!")
	private LocalDate childrenDateOfBirth;

	@NotNull(message = "Specify the Gender!!!")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotBlank(message = "Specify the Blood type!!!")
	private String bloodType;

}
