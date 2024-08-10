package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccineDto {

	@NotBlank(message = "Vaccine name are Mandatory!!!")
	private String vaccineName;

	@NotBlank(message = "Description are Mandatory!!!")
	private String description;

	@NotBlank(message = "Recommended Age are Mandatory!!!")
	private String recommendedAge;

	@NotBlank(message = "Side Effect are Mandatory!!!")
	private String sideEffect;

}
