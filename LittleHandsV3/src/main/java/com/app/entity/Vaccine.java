package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vaccines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@NotBlank(message = "Vaccine name are Mandatory!!!")
	@Column(nullable = false)
	private String vaccineName;
	
	@NotBlank(message = "Description are Mandatory!!!")
	@Column(nullable = false)
	private String description;
	
	@NotBlank(message = "Recommended Age are Mandatory!!!")
	@Column(nullable = false)
	private String recommendedAge;
	
	@NotBlank(message = "Side Effect are Mandatory!!!")
	@Column(nullable = false)
	private String sideEffect;
}
