package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserProfileDto;
import com.app.service.UserProfileService;

@RestController
@RequestMapping("/userprofiles")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@PostMapping("/createuser")
	public ResponseEntity<?> createNewUserProfile(@Valid @RequestBody UserProfileDto userProfileDto) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userProfileService.createNewUserProfile(userProfileDto));
	}

	@GetMapping("/getallusers")
	public ResponseEntity<?> getAllUserProfList() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userProfileService.getAllUserProfList());
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<?> updateUserProfile(@PathVariable Long id,
			@Valid @RequestBody UserProfileDto userProfileDto) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userProfileService.updateUserProfile(id, userProfileDto));
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<?> deleteUserProfile(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userProfileService.deleteUserProfile(id));
	}
}
