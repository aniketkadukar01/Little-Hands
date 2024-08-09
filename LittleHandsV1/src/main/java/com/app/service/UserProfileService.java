package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.UserProfileDto;

public interface UserProfileService {
		
	ApiResponse createNewUserProfile(UserProfileDto userProfileDto);
	
	List<UserProfileDto> getAllUserProfList();
	
	UserProfileDto updateUserProfile(Long id ,UserProfileDto userProfileDto);
	
	ApiResponse deleteUserProfile(Long id);
}
