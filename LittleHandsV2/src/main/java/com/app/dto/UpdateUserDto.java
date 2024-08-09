package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUserDto {

	@NotBlank(message = "Please enter your first name!!!")
	private String firstName;

	@NotBlank(message = "Please enter your last name!!!")
	private String lastName;

	@NotBlank(message = "Email is Mandatory!!!")
	@Email(message = "Email is Invalid!!!")
	private String email;

	@NotBlank(message = "Password is Mandatory!!!")
	private String password;

	@NotBlank(message = "Phone Number is Mandatory!!!")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
	@Pattern(regexp = "\\d{10}", message = "Phone number must contain only digits")
	private String phoneNumber;

	@NotBlank(message = "Enter your Address!!!")
	private String address;
}
