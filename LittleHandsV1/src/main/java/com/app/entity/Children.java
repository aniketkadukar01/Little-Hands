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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "childrens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Children {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@NotBlank(message = "Please enter your first name!!!")
	@Column(nullable = false)
	private String firstName;

	@NotBlank(message = "Please enter your last name!!!")
	@Column(nullable = false)
	private String lastName;

	@NotNull(message = "Date of Birth is Mandatory!!!")
	@Past(message = "Date must be in past!!!")
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate childrenDateOfBirth;

	@NotNull(message = "Specify the Gender!!!")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@NotBlank(message = "Specify the Blood type!!!")
	@Column(nullable = false)
	private String bloodType;

	@NotNull(message = "Parent details is Mandatory!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_profile_id", nullable = false, referencedColumnName = "id")
	private UserProfile userProfile;

}
