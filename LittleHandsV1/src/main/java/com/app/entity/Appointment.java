package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@NotNull(message = "Children details are mandatory!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "children_id", nullable = false , referencedColumnName = "id")
	private Children children;
	
	@NotNull(message = "User Profile details are mandatory!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_profile_id", nullable = false , referencedColumnName = "id")
	private UserProfile userProfile;
	
	@NotNull(message = "Vaccine details are Mandatory!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vaccine_id", nullable = false, referencedColumnName = "id")
	private Vaccine vaccine;

	@NotBlank(message = "Clinic name is mandatory!!!")
	@Column(nullable = false)
	private String clinicName;

	@NotNull(message = "Appointment date is Mandatory!!!")
	@FutureOrPresent
	@Column(nullable = false)
	private LocalDate appointmentDate;

	private String reason;

	@NotNull(message = "Status is Mandatory!!!")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;
}
